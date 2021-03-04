package io.yugoal.lib_base;

import android.app.Application;
import android.content.Context;

/**
 * user caoyu
 * date 2021/3/4
 * time 14:40
 */
public class BaseApp extends Application {
    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }
}
