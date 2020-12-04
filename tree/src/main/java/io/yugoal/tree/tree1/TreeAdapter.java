package io.yugoal.tree.tree1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.LinkedList;
import java.util.Queue;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.recyclerview.BaseViewHolder;
import io.yugoal.lib_common_ui.arouter.IAppService;
import io.yugoal.lib_common_ui.arouter.RouteServiceManager;
import io.yugoal.tree.R;
import io.yugoal.tree.treelist.TreeListActivity;
import io.yugoal.tree.views.TreeItem2Model;
import io.yugoal.tree.views.TreeItemModel;
import io.yugoal.tree.views.TreeItemView;


/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class TreeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "TreeAdapter";
    private ObservableList<BaseCustomViewModel> mItems;
    private Context context;
    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();

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
        return new BaseViewHolder(treeItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        TreeItemModel treeBean = (TreeItemModel) mItems.get(position);
        TreeItemView treeItemView = (TreeItemView) holder.getView();
        FlexboxLayout fbl = treeItemView.getViewDataBinding().fbl;
        for (int i = 0; i < treeBean.item2Model.size(); i++) {
            TreeItem2Model childItem = treeBean.item2Model.get(i);
            TextView child = createOrGetCacheFlexItemTextView(fbl);
            child.setText(childItem.name);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TreeListActivity.show(v.getContext(),childItem.id,childItem.name);
                }
            });
            fbl.addView(child);
        }
        holder.bind(treeBean);
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        TreeItemView treeItemView = (TreeItemView) holder.getView();
        for (int i = 0; i < treeItemView.getViewDataBinding().fbl.getChildCount(); i++) {
            mFlexItemTextViewCaches.offer((TextView) treeItemView.getViewDataBinding().fbl.getChildAt(i));
        }
        treeItemView.getViewDataBinding().fbl.removeAllViews();
    }

    private TextView createOrGetCacheFlexItemTextView(FlexboxLayout fbl) {
        TextView tv = mFlexItemTextViewCaches.poll();
        if (tv != null) {
            return tv;
        }
        return createFlexItemTextView(fbl);
    }

    private TextView createFlexItemTextView(FlexboxLayout fbl) {
        return (TextView) LayoutInflater.from(fbl.getContext()).inflate(R.layout.item_tree2, fbl, false);
    }
}
