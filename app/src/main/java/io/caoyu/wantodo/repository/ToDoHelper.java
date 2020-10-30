package io.caoyu.wantodo.repository;

import android.content.Context;

import io.caoyu.wantodo.model.ToDoBean;


/**
 * @user caoyu
 * @date 2019/11/14
 */
public final class ToDoHelper {
    //SDK全局Context, 供子模块用
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void addToDo(ToDoBean toDoBean) {
        ToDoController.getInstance().addToDo(toDoBean);
    }

    public static void deleteToDo(ToDoBean toDoBean) {
        ToDoController.getInstance().deleteToDo(toDoBean);
    }

    public static void updateToDo(ToDoBean toDoBean) {
        ToDoController.getInstance().updataToDo(toDoBean);
    }

    public static void getToDoList(int type, int priority, int orderby,int page) {
        ToDoController.getInstance().getToDoList(type, priority, orderby,page);
    }

    public static Context getContext() {
        return mContext;
    }
}
