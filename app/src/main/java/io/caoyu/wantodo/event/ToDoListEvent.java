package io.caoyu.wantodo.event;

import java.util.List;

import io.caoyu.wantodo.model.ToDoBean;

/**
 * @user caoyu
 * @date 2019/11/14
 */
public class ToDoListEvent {


    private List<ToDoBean> list;


    public List<ToDoBean> getList() {
        return list;
    }

    public void setList(List<ToDoBean> list) {
        this.list = list;
    }
}
