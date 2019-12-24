package io.yugoal.lib_todo.core;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.yugoal.lib_todo.db.GreenDaoHelper;
import io.yugoal.lib_todo.event.ToDoListEvent;
import io.yugoal.lib_todo.model.ToDoBean;

/**
 * @user caoyu
 * @date 2019/11/14
 * 数据中转站
 */
public class Repository {
    private boolean isLogin;

    public static Repository getInstance() {
        return Repository.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static Repository instance = new Repository();
    }


    public void addToDo(ToDoBean toDoBean) {
        if (isLogin) {

        } else {
            GreenDaoHelper.addToDo(toDoBean);
        }
    }

    public void deleteToDo(int id) {
        if (isLogin) {

        } else {
            GreenDaoHelper.deleteToDo(id);
        }
    }


    public void updataToDo(ToDoBean toDoBean) {
        if (isLogin) {

        } else {
            GreenDaoHelper.updataToDo(toDoBean);
        }
    }

    public void getToDoList(int status, int type, int priority, int orderby) {
        if (isLogin) {

        } else {
            List<ToDoBean> list = GreenDaoHelper.getToDoList(status, type, priority, orderby);
            ToDoListEvent toDoListEvent = new ToDoListEvent(status != 0);
            toDoListEvent.setList(list);
            EventBus.getDefault().post(toDoListEvent);
        }
    }

    public void completedToDo(int id, int status) {
        if (isLogin) {

        } else {
            GreenDaoHelper.completedToDo(id, status);
        }
    }
}
