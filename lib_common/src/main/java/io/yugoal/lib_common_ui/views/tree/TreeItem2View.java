package io.yugoal.lib_common_ui.views.tree;

import android.content.Context;
import android.view.View;

import io.yugoal.lib_base.base.customview.BaseCustomView;
import io.yugoal.lib_common_ui.R;
import io.yugoal.lib_common_ui.databinding.ItemTree2Binding;

/**
 * user caoyu
 * date 2020/11/26
 * time 16:27
 */
public class TreeItem2View extends BaseCustomView<ItemTree2Binding, TreeItem2Model> {
    public TreeItem2View(Context context) {
        super(context);
    }



    @Override
    public int setViewLayoutId() {
        return R.layout.item_tree2;
    }

    @Override
    protected void setDataToView(TreeItem2Model data) {
        getDataBinding().setTree2(data);
    }

    @Override
    public void onRootClick(View view) {
        //WebviewActivity.startCommonWeb(view.getContext(), "", getViewModel().link);
    }
}