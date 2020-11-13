package io.caoyu.wantodo.ui.wenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.api.bean.WendaBean;
import io.caoyu.wantodo.databinding.ItemAllBinding;
import io.caoyu.wantodo.databinding.ItemWendaBinding;
import io.yugoal.lib_utils.utils.StringUtils;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class WendaAdapter extends RecyclerView.Adapter<WendaAdapter.ViewHolder> {


    private List<WendaBean.DatasDTO> stepList;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<WendaBean.DatasDTO> myLiveList);
    }

    public WendaAdapter(Context mContext, List<WendaBean.DatasDTO> stepList) {
        this.stepList = stepList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(ItemWendaBinding.inflate(mInflater, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        WendaBean.DatasDTO datasBean = stepList.get(position);
        holder.binding.tvAuthor.setText(datasBean.getAuthor());
        holder.binding.tvNiceShareDate.setText(datasBean.getNiceDate());
        holder.binding.tvTags.setText(String.format("%s·%s", datasBean.getSuperChapterName(), datasBean.getChapterName()));
        holder.binding.tvTitle.setText(datasBean.getTitle());
        holder.binding.tvDesc.setText(StringUtils.getHTMLStr(datasBean.getDesc()));
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
        private ItemWendaBinding binding;

        public ViewHolder(ItemWendaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
