package io.caoyu.wantodo.application;

import android.app.Application;

import io.caoyu.wantodo.repository.ToDoHelper;


/**
 * @user caoyu
 * @date 2019/11/13
 */
public class TodoApplication extends Application {

    private static TodoApplication todoApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        todoApplication = this;
        ToDoHelper.init(this);
    }

    public static TodoApplication getInstance() {
        return todoApplication;
    }
}
