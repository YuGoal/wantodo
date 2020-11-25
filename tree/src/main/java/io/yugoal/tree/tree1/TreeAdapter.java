package io.yugoal.tree.tree1;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.recyclerview.BaseViewHolder;
import io.yugoal.lib_common_ui.views.tree.TreeItemView;


/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class TreeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private ObservableList<BaseCustomViewModel> mItems;

    public TreeAdapter() {

    }
    public void setData(ObservableList<BaseCustomViewModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TreeItemView treeItemView = new TreeItemView(parent.getContext());
        return new BaseViewHolder(treeItemView);
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
