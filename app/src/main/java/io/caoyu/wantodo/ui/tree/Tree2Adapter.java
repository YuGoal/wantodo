package io.caoyu.wantodo.ui.tree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import io.caoyu.wantodo.api.bean.TreeBean;
import io.caoyu.wantodo.databinding.ItemTree2Binding;
import io.caoyu.wantodo.databinding.ItemTreeBinding;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class Tree2Adapter extends RecyclerView.Adapter<Tree2Adapter.ViewHolder> {


    private List<TreeBean.ChildrenBean> stepList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private Context context;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<TreeBean.ChildrenBean> myLiveList);
    }

    public Tree2Adapter(Context mContext, List<TreeBean.ChildrenBean> stepList) {
        this.stepList = stepList;
        context = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(ItemTree2Binding.inflate(mInflater, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TreeBean.ChildrenBean dataBean = stepList.get(position);
        holder.binding.btnTitle.setText(dataBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), stepList);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (stepList == null) {
            return 0;
        }
        return stepList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTree2Binding binding;

        public ViewHolder(ItemTree2Binding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
