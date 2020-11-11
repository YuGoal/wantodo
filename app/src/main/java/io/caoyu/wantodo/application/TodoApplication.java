package io.caoyu.wantodo.application;

import io.caoyu.wantodo.repository.ToDoHelper;
import io.yugoal.lib_base.App;


/**
 * @user caoyu
 * @date 2019/11/13
 */
public class TodoApplication extends App {

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
