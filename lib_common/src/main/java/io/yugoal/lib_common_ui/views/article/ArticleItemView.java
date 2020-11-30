package io.yugoal.lib_common_ui.views.article;

import android.content.Context;
import android.view.View;

import io.yugoal.lib_base.base.customview.BaseCustomView;
import io.yugoal.lib_common_ui.R;
import io.yugoal.lib_common_ui.arouter.IAppService;
import io.yugoal.lib_common_ui.arouter.RouteServiceManager;
import io.yugoal.lib_common_ui.databinding.ItemArticleBinding;


public class ArticleItemView extends BaseCustomView<ItemArticleBinding, ArticleItemModel> {
    public ArticleItemView(Context context) {
        super(context);
    }



    @Override
    public int setViewLayoutId() {
        return R.layout.item_article;
    }

    @Override
    protected void setDataToView(ArticleItemModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    public void onRootClick(View view) {
        IAppService iAppService = RouteServiceManager.provide(IAppService.class, IAppService.APP_SERVICE);
        assert iAppService != null;
        iAppService.showWebview(view.getContext(),getViewModel().link,getViewModel().title,getViewModel().author);
    }
}
