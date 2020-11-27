package io.yugoal.wenda.ui;

import android.content.Context;
import android.view.View;

import io.yugoal.lib_base.base.customview.BaseCustomView;
import yugoal.wenda.R;
import yugoal.wenda.databinding.ItemWendaBinding;


public class WendaItemView extends BaseCustomView<ItemWendaBinding, WendaItemModel> {
    public WendaItemView(Context context) {
        super(context);
    }



    @Override
    public int setViewLayoutId() {
        return R.layout.item_wenda;
    }

    @Override
    protected void setDataToView(WendaItemModel data) {
        getDataBinding().setWenda(data);
    }

    @Override
    public void onRootClick(View view) {
        //WebviewActivity.startCommonWeb(view.getContext(), "", getViewModel().link);
    }
}
