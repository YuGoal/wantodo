package io.caoyu.wantodo.application;

import android.app.Application;

import io.caoyu.wantodo.constant.Constant;
import io.caoyu.wantodo.repository.ToDoHelper;
import io.yugoal.lib_common_ui.utils.SpUtils;


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
        SpUtils.init(getSharedPreferences(Constant.FILE_NAME, MODE_PRIVATE));
    }

    public static TodoApplication getInstance() {
        return todoApplication;
    }
}
