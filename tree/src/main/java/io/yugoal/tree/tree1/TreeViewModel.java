package io.yugoal.tree.tree1;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:54
 */
public class TreeViewModel extends MvvmBaseViewModel<TreeModel, BaseCustomViewModel> {
    public TreeViewModel init() {
        model = new TreeModel();
        model.register(this);
        //model.getCachedDataAndLoad();
        model.refresh();
        return this;
    }
}
