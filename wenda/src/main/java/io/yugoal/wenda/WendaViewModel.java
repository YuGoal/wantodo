package io.yugoal.wenda;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:54
 */
public class WendaViewModel extends MvvmBaseViewModel<WendaModel, BaseCustomViewModel> {
    public WendaViewModel init() {
        model =new WendaModel();
        model.register(this);
        //model.getCachedDataAndLoad();
        model.refresh();
        return this;
    }
    public void tryToLoadNextPage() {
        model.loadNexPage();
    }
}
