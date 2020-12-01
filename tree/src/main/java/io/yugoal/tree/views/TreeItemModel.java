package io.yugoal.tree.views;

import androidx.databinding.ObservableArrayList;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;

/**
 * user caoyu
 * date 2020/11/18
 * time 17:44
 * 体系列表单项展示数据
 */
public class TreeItemModel extends BaseCustomViewModel {
    public String name;
    public ObservableArrayList<TreeItem2Model> item2Model;
}
