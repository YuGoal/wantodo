package io.caoyu.wantodo.repository;

import android.content.Context;

import io.caoyu.wantodo.db.GreenDaoHelper;
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
        //初始化本地数据库
        GreenDaoHelper.initDatabase();
    }

    public static void addToDo(ToDoBean toDoBean) {
        ToDoController.getInstance().addToDo(toDoBean);
    }

    public static void deleteToDo(long id) {
        ToDoController.getInstance().deleteToDo(id);
    }

    public static void updataToDo(ToDoBean toDoBean) {
        ToDoController.getInstance().updataToDo(toDoBean);
    }

    public static void getToDoList(int type, int priority, int orderby) {
        ToDoController.getInstance().getToDoList(type, priority, orderby);
    }

    public static void completedToDo(long id, int status) {
        ToDoController.getInstance().completedToDo(id, status);
    }

    public static Context getContext() {
        return mContext;
    }
}
