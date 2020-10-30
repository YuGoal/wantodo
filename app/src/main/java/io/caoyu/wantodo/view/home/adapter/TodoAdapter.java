package io.caoyu.wantodo.view.home.adapter;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.caoyu.wantodo.databinding.RvFooterBinding;
import io.caoyu.wantodo.databinding.TodoItemBinding;
import io.caoyu.wantodo.model.ToDoBean;

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
            return new ContentViewHolder(TodoItemBinding.inflate(mInflater,parent,false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(RvFooterBinding.inflate(mInflater,parent,false));
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
            if (TextUtils.isEmpty(toDoBean.content)) {
                contentViewHolder.binding.tvTodo.setVisibility(View.GONE);
            } else {
                contentViewHolder.binding.tvTodo.setVisibility(View.VISIBLE);
            }
            if (0==toDoBean.status){
                //待完成
                contentViewHolder.binding.checkbox.setChecked(false);
            }else {
                contentViewHolder.binding.checkbox.setChecked(true);
                //已完成
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    contentViewHolder.itemView.setTransitionAlpha(0.4f);
                }
            }
            contentViewHolder.binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    toDoCallBack.completedToDo(position);
                }
            });
            contentViewHolder.binding.tvDate.setText(toDoBean.dateStr);
            contentViewHolder.binding.tvTodoTitle.setText(toDoBean.title);
            contentViewHolder.binding.tvTodo.setText(toDoBean.content);
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
    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        private TodoItemBinding binding;
        public ContentViewHolder(TodoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        private RvFooterBinding footerBinding;
        public BottomViewHolder(RvFooterBinding itemView) {
            super(itemView.getRoot());
            footerBinding = itemView;
        }
    }

    public interface ToDoCallBack {
        void deleteToDo(int positon);

        void completedToDo(int positon);
    }
}