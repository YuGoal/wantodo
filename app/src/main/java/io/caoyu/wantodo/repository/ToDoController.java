package io.caoyu.wantodo.repository;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.caoyu.wantodo.db.TodoDao;
import io.caoyu.wantodo.db.TodoDataBase;
import io.caoyu.wantodo.event.ToDoListEvent;
import io.caoyu.wantodo.model.ToDoBean;

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
        TodoDataBase.getInstance().getTodoDao().addToDo(toDoBean);
    }

    public void deleteToDo(ToDoBean toDoBean) {
        TodoDataBase.getInstance().getTodoDao().deleteToDo(toDoBean);
    }


    public void updataToDo(ToDoBean toDoBean) {
        TodoDataBase.getInstance().getTodoDao().updateToDo(toDoBean);
    }

    public void getToDoList(int type, int priority, int orderby,int page) {
        List<ToDoBean> list = TodoDataBase.getInstance().getTodoDao().getToDoList(type, priority, orderby,page);
        ToDoListEvent toDoListEvent = new ToDoListEvent();
        toDoListEvent.setList(list);
        EventBus.getDefault().post(toDoListEvent);
    }
}
