package io.yugoal.wenda.ui;

import android.content.Context;
import android.view.View;

import io.yugoal.lib_base.base.customview.BaseCustomView;
import io.yugoal.lib_common_ui.arouter.IAppService;
import io.yugoal.lib_common_ui.arouter.RouteServiceManager;
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
        IAppService iAppService = RouteServiceManager.provide(IAppService.class, IAppService.APP_SERVICE);
        assert iAppService != null;
        iAppService.showWebview(view.getContext(),getViewModel().link,getViewModel().title,getViewModel().author);
    }
}
