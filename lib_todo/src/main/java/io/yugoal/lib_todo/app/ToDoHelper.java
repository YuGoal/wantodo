package io.yugoal.lib_todo.app;

import android.content.Context;

import java.util.List;

import io.yugoal.lib_todo.core.ToDoController;
import io.yugoal.lib_todo.db.GreenDaoHelper;
import io.yugoal.lib_todo.model.ToDoBean;

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

    public static void deleteToDo(int id) {
        ToDoController.getInstance().deleteToDo(id);
    }


    public static void updataToDo(ToDoBean toDoBean) {
        ToDoController.getInstance().updataToDo(toDoBean);
    }

    public static void getToDoList(int status, int type, int priority, int orderby) {
        ToDoController.getInstance().getToDoList(status, type, priority, orderby);
    }

    public static void completedToDo(int id, int status) {
        ToDoController.getInstance().completedToDo(id, status);
    }

    public static Context getContext() {
        return mContext;
    }
}
