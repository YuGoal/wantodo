package io.yugoal.lib_todo.event;

import java.util.List;

import io.yugoal.lib_todo.model.ToDoBean;

/**
 * @user caoyu
 * @date 2019/11/14
 */
public class ToDoListEvent {
    public boolean isCompleted;

    private List<ToDoBean> list;

    public ToDoListEvent(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public List<ToDoBean> getList() {
        return list;
    }

    public void setList(List<ToDoBean> list) {
        this.list = list;
    }
}
