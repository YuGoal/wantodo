package io.caoyu.wantodo.view.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.caoyu.wantodo.R;
import io.caoyu.wantodo.db.ToDoBean;

/**
 * @user caoyu
 * @date 2019/11/15
 */
public class TodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecycleItemTouchHelper.ItemTouchHelperCallback {

    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;


    private Context mContext;
    private List<ToDoBean> stepList;
    private LayoutInflater mInflater;

    private ToDoCallBack toDoCallBack;

    private int mBottomCount = 1;//底部View个数

    public void setToDoCallBack(ToDoCallBack toDoCallBack) {
        this.toDoCallBack = toDoCallBack;
    }

    public TodoAdapter(Context mContext, List<ToDoBean> stepList) {
        this.mContext = mContext;
        this.stepList = stepList;
        mInflater = LayoutInflater.from(mContext);
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (getItemCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mInflater.inflate(R.layout.todo_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(mInflater.inflate(R.layout.rv_footer, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getItemCount();
        if (mBottomCount != 0 && position >= (dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ToDoBean toDoBean = stepList.get(position);
        if (holder instanceof ContentViewHolder) {
            ContentViewHolder  contentViewHolder = ((ContentViewHolder) holder);
            if (TextUtils.isEmpty(toDoBean.getContent())) {
                contentViewHolder.tvTodo.setVisibility(View.GONE);
            } else {
                contentViewHolder.tvTodo.setVisibility(View.VISIBLE);
            }
            if (0==toDoBean.getStatus()){
                //待完成
                contentViewHolder.checkBox.setChecked(false);
            }else {
                contentViewHolder.checkBox.setChecked(true);
                //已完成
                contentViewHolder.itemView.setTransitionAlpha(0.4f);
            }
            contentViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    toDoCallBack.completedToDo(position);
                }
            });
            contentViewHolder.tvDate.setText(toDoBean.getDateStr());
            contentViewHolder.tvTodoTitle.setText(toDoBean.getTitle());
            contentViewHolder.tvTodo.setText(toDoBean.getContent());
        } else if (holder instanceof BottomViewHolder) {

        }

    }

    @Override
    public int getItemCount() {
        if (stepList == null) {
            return 0;
        }
        return stepList.size();
    }

    @Override
    public void onItemL(int positon) {
        toDoCallBack.deleteToDo(positon);
    }

    @Override
    public void onItemR(int positon) {
        toDoCallBack.completedToDo(positon);
    }


    //内容 ViewHolder
    public class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_todo_title)
        TextView tvTodoTitle;
        @BindView(R.id.tv_todo)
        TextView tvTodo;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.checkbox)
        CheckBox checkBox;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        public BottomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ToDoCallBack {
        void deleteToDo(int positon);

        void completedToDo(int positon);
    }
}