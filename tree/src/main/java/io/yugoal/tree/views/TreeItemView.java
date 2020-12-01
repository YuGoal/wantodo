package io.yugoal.tree.views;

import android.content.Context;
import android.view.View;

import io.yugoal.lib_base.base.customview.BaseCustomView;
import io.yugoal.tree.R;
import io.yugoal.tree.databinding.ItemTreeBinding;


public class TreeItemView extends BaseCustomView<ItemTreeBinding, TreeItemModel> {
    public TreeItemView(Context context) {
        super(context);
    }


    @Override
    public int setViewLayoutId() {
        return R.layout.item_tree;
    }

    @Override
    protected void setDataToView(TreeItemModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    public void onRootClick(View view) {
        //WebviewActivity.startCommonWeb(view.getContext(), "", getViewModel().item2Model);
    }

    public ItemTreeBinding getViewDataBinding() {
        return getDataBinding();
    }
}
