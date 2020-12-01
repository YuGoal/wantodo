package io.yugoal.tree.tree1;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.recyclerview.BaseViewHolder;
import io.yugoal.lib_common_ui.arouter.IAppService;
import io.yugoal.lib_common_ui.arouter.RouteServiceManager;
import io.yugoal.tree.views.TreeItemModel;
import io.yugoal.tree.views.TreeItemView;
import io.yugoal.tree.tree2.Tree2Adapter;


/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class TreeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "TreeAdapter";
    private ObservableList<BaseCustomViewModel> mItems;
    private Context context;
    private RecyclerView recyclerView;

    public TreeAdapter() {
        IAppService iAppService = RouteServiceManager.provide(IAppService.class, IAppService.APP_SERVICE);
        context = iAppService.getApp();
    }

    public void setData(ObservableList<BaseCustomViewModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TreeItemView treeItemView = new TreeItemView(parent.getContext());
        recyclerView = treeItemView.getViewDataBinding().recyclerView;
        return new BaseViewHolder(treeItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        TreeItemModel treeBean = (TreeItemModel) mItems.get(position);

        Tree2Adapter tree2Adapter = new Tree2Adapter();
        tree2Adapter.setData(treeBean.item2Model);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(tree2Adapter);
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
