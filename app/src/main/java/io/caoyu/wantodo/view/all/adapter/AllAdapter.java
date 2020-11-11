package io.caoyu.wantodo.view.all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.databinding.ItemAllBinding;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {


    private List<ArticleBean.DatasBean> stepList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<ArticleBean.DatasBean> myLiveList);
    }

    public AllAdapter(Context mContext, List<ArticleBean.DatasBean> stepList) {
        this.stepList = stepList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(ItemAllBinding.inflate(mInflater, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ArticleBean.DatasBean datasBean = stepList.get(position);
        holder.binding.tvAuthor.setText(datasBean.getShareUser());
        holder.binding.tvNiceShareDate.setText(datasBean.getNiceShareDate());
        holder.binding.tvTags.setText(datasBean.getSuperChapterName());
        holder.binding.tvTitle.setText(datasBean.getTitle());
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
        private ItemAllBinding binding;

        public ViewHolder(ItemAllBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
