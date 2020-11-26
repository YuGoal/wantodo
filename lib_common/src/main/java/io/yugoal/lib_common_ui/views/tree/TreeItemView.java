package io.yugoal.lib_common_ui.views.tree;

import android.content.Context;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import io.yugoal.lib_base.base.customview.BaseCustomView;
import io.yugoal.lib_common_ui.R;
import io.yugoal.lib_common_ui.databinding.ItemTreeBinding;


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
