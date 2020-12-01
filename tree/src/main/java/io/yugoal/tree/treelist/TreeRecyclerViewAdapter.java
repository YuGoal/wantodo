package io.yugoal.tree.treelist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.recyclerview.BaseViewHolder;
import io.yugoal.lib_common_ui.views.article.ArticleItemView;

/**
 * user caoyu
 * date 2020/11/18
 * time 17:39
 */
public class TreeRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ObservableList<BaseCustomViewModel> mItems;

    public TreeRecyclerViewAdapter() {
    }

    public void setData(ObservableList<BaseCustomViewModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArticleItemView articleItemView = new ArticleItemView(parent.getContext());
        return new BaseViewHolder(articleItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }
}
