package io.yugoal.tree.treelist;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;

/**
 * user caoyu
 * date 2020/12/1
 * time 15:25
 */
public class TreeListViewModel extends MvvmBaseViewModel<TreeListModel, BaseCustomViewModel> {



    public TreeListViewModel init(int cid) {
        model = new TreeListModel();
        model.setCid(cid);
        model.register(this);
        //model.getCachedDataAndLoad();
        model.refresh();
        return this;
    }

    public void tryToLoadNextPage() {
        model.loadNexPage();
    }
}
