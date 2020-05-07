package io.yugoal.lib_todo.core;

import io.yugoal.lib_todo.model.ToDoBean;

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
        Repository.getInstance().addToDo(toDoBean);
    }

    public void deleteToDo(int id) {
        Repository.getInstance().deleteToDo(id);
    }


    public void updataToDo(ToDoBean toDoBean) {
        Repository.getInstance().updataToDo(toDoBean);
    }

    public void getToDoList(int status, int type, int priority, int orderby) {
        Repository.getInstance().getToDoList(status, type, priority, orderby);
    }

    public void completedToDo(int id, int status) {
        Repository.getInstance().completedToDo(id, status);
    }
}
