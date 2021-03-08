package io.yugoal.article.favorites;

import io.yugoal.article.ArticleModel;
import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:54
 */
public class FavoritesViewModel extends MvvmBaseViewModel<FavoritesModel, BaseCustomViewModel> {

    public FavoritesViewModel init() {
        model =new FavoritesModel();
        model.register(this);
        //model.getCachedDataAndLoad();
        model.refresh();
        return this;
    }
    public void tryToLoadNextPage() {
        model.loadNexPage();
    }

}
