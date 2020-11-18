package io.yugoal.article;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:54
 */
public class ArticleViewModel extends MvvmBaseViewModel<ArticleModel, BaseCustomViewModel> {

    public ArticleViewModel init() {
        model =new ArticleModel();
        model.register(this);
        model.getCachedDataAndLoad();
        return this;
    }
    public void tryToLoadNextPage() {
        model.loadNexPage();
    }
}
