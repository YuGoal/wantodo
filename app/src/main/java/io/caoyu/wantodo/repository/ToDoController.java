package io.caoyu.wantodo.repository;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.caoyu.wantodo.db.GreenDaoHelper;
import io.caoyu.wantodo.event.ToDoListEvent;
import io.caoyu.wantodo.db.ToDoBean;

/**
 * @user caoyu
 * @date 2019/11/14
 */
public class ToDoController {

    public static ToDoController getInstance() {
        return ToDoController.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static ToDoController instance = new ToDoController();
    }

    public void addToDo(ToDoBean toDoBean) {
        GreenDaoHelper.addToDo(toDoBean);
    }

    public void deleteToDo(long id) {
        GreenDaoHelper.deleteToDo(id);
    }


    public void updataToDo(ToDoBean toDoBean) {
        GreenDaoHelper.updataToDo(toDoBean);
    }

    public void getToDoList(int type, int priority, int orderby) {
        List<ToDoBean> list = GreenDaoHelper.getToDoList(type, priority, orderby);
        ToDoListEvent toDoListEvent = new ToDoListEvent();
        toDoListEvent.setList(list);
        EventBus.getDefault().post(toDoListEvent);
    }

    public void completedToDo(long id, int status) {
        GreenDaoHelper.completedToDo(id, status);
    }
}
