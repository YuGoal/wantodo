package io.caoyu.wantodo.view.home.adapter;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.application.TodoApplication;
import io.yugoal.lib_common_ui.utils.UiUtil;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

/**
 * @user caoyu
 * @date 2019/11/15
 * RecycleView滑动
 */
public class RecycleItemTouchHelper extends ItemTouchHelper.Callback {
    private static final String TAG = "RecycleItemTouchHelper";
    private Resources resources;
    private int padding;//灰色背景的宽度padding
    private int linePadding;//对勾的宽度
    //            int maxDrawWidth=2*padding+bitmap.getWidth();//最大的绘制宽度
    //背景画笔
    private Paint paint;
    //对勾画笔
    private Paint mPaintTick;
    //文字画笔
    private Paint mStrTick;
    //记录打钩路径的三个点坐标
    private float[] mPoints = new float[8];

    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;

    private ItemTouchHelperCallback helperCallback;


    public RecycleItemTouchHelper(ItemTouchHelperCallback helperCallback) {
        this.helperCallback = helperCallback;
        resources = TodoApplication.getInstance().getResources();
        padding = UiUtil.dip2px(TodoApplication.getInstance(), 60);//图片绘制的padding
        linePadding = UiUtil.dip2px(TodoApplication.getInstance(), 16);//图片绘制的padding
        //背景画笔
        paint = new Paint();
        mPaintTick = new Paint();
        //对勾画笔
        mPaintTick.setColor(resources.getColor(R.color.white));
        mPaintTick.setStrokeCap(Paint.Cap.ROUND);
        mPaintTick.setStrokeWidth(UiUtil.dip2px(TodoApplication.getInstance(), 2.4f));
        //文字画笔
        mStrTick = new Paint();
        mStrTick.setColor(resources.getColor(R.color.white));
        mStrTick.setTextSize(UiUtil.dip2px(TodoApplication.getInstance(), 14));
    }

    /**
     * 设置滑动类型标记
     *
     * @param recyclerView
     * @param viewHolder
     * @return 返回一个整数类型的标识，用于判断Item那种移动行为是允许的
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //START  右向左 END左向右 LEFT  向左 RIGHT向右  UP向上
        //如果某个值传0，表示不触发该操作，次数设置支持上下拖拽，支持向右滑动
        return makeMovementFlags(0, LEFT | RIGHT);
    }


    /**
     * Item是否支持长按拖动
     *
     * @return true  支持长按操作
     * false 不支持长按操作
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * Item是否支持滑动
     *
     * @return true  支持滑动操作
     * false 不支持滑动操作
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        switch (direction) {
            case LEFT:
                //左滑
                helperCallback.onItemL(viewHolder.getAdapterPosition());
                break;
            case RIGHT:
                //右滑
                helperCallback.onItemR(viewHolder.getAdapterPosition());
                break;
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //滑动时自己实现背景及图片
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //dX大于0时向右滑动，小于0向左滑动
            View itemView = viewHolder.itemView;//获取滑动的view

            int x = Math.round(Math.abs(dX));

            //1.向右滑动
            if (dX > 0) {
                //每一项的高
                int h = itemView.getBottom() - itemView.getTop();
                if (x > padding) {
                    //滑动距离大于padding时开始变色
                    paint.setColor(resources.getColor(R.color.bg_completed));
                    x1 = x - ((padding / 2) + (linePadding / 2));
                    y1 = itemView.getTop() + h / 2;
                    x2 = x - padding / 2;
                    y2 = itemView.getTop() + (h / 2 + linePadding / 2);
                    x3 = x - padding / 2 + linePadding;
                    y3 = itemView.getTop() + (h / 2 - linePadding / 2);
                } else {
                    //滑动距离小于padding时是灰色
                    paint.setColor(resources.getColor(R.color.todo));
                    //缩小点
                    x1 = padding / 2 - 20;
                    y1 = itemView.getTop() + h / 2;
                    x2 = padding / 2;
                    y2 = itemView.getTop() + (h / 2 + linePadding / 2);
                    x3 = padding / 2 + linePadding;
                    y3 = itemView.getTop() + (h / 2 - linePadding / 2);
                }
                //2.根据滑动实时绘制一个背景
                c.drawRect(itemView.getLeft(), itemView.getTop(), x, itemView.getBottom(), paint);
                //3.绘制对勾
                //指定对勾绘制的位置，一个对勾需要三个点
                mPoints[0] = x1;
                mPoints[1] = y1;
                mPoints[2] = x2;
                mPoints[3] = y2;
                mPoints[4] = x2;
                mPoints[5] = y2;
                mPoints[6] = x3;
                mPoints[7] = y3;
                c.drawLines(mPoints, mPaintTick);

                //绘制时需调用平移动画，否则滑动看不到反馈
                itemView.setTranslationX(dX);
            } else {
                //每一项的高
                int textX;
                int h = itemView.getBottom() - itemView.getTop();
                int w = itemView.getRight() - itemView.getLeft();
                int textY = itemView.getTop() + h / 2 + 10;
                if (x > padding) {
                    //滑动距离大于padding时开始变色
                    paint.setColor(resources.getColor(R.color.bg_todo));
                    textX = x - (padding / 2) + 20;
                } else {
                    //滑动距离小于padding时是灰色
                    paint.setColor(resources.getColor(R.color.todo));
                    //缩小点
                    textX = padding / 2 + 20;
                }
                //画背景
                c.drawRect(itemView.getRight(), itemView.getTop(), itemView.getWidth() - x, itemView.getBottom(), paint);
                //画文字
                c.drawText("删除", w - textX, textY, mStrTick);
                //绘制时需调用平移动画，否则滑动看不到反馈
                itemView.setTranslationX(dX);
            }
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    /**
     * Item被选中时候回调
     *
     * @param viewHolder
     * @param actionState 当前Item的状态
     *                    ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
     *                    ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
     *                    ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    //滑动接口回调
    public interface ItemTouchHelperCallback {
        void onItemR(int positon);

        void onItemL(int positon);
    }
}
