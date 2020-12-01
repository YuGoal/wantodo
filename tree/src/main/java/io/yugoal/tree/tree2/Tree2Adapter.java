package io.yugoal.tree.tree2;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import io.yugoal.lib_base.base.recyclerview.BaseViewHolder;
import io.yugoal.tree.views.TreeItem2Model;
import io.yugoal.tree.views.TreeItem2View;


/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class Tree2Adapter extends RecyclerView.Adapter<BaseViewHolder> {
    private ObservableList<TreeItem2Model> mItems;

    public Tree2Adapter() {

    }
    public void setData(ObservableArrayList<TreeItem2Model> items) {
        mItems = items;
        notifyDataSetChanged();
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TreeItem2View treeItemView = new TreeItem2View(parent.getContext());
        return new BaseViewHolder(treeItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        TreeItem2Model treeBean = (TreeItem2Model) mItems.get(position);
        holder.bind(treeBean);
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }
}

