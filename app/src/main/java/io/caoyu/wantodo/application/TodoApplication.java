package io.caoyu.wantodo.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kingja.loadsir.core.LoadSir;

import io.caoyu.wantodo.BuildConfig;
import io.yugoal.lib_base.base.preference.PreferencesUtil;
import io.yugoal.lib_utils.utils.ToastUtil;
import io.yugoal.lib_base.loadsir.callback.CustomCallback;
import io.yugoal.lib_base.loadsir.callback.EmptyCallback;
import io.yugoal.lib_base.loadsir.callback.ErrorCallback;
import io.yugoal.lib_base.loadsir.callback.LoadingCallback;
import io.yugoal.lib_base.loadsir.callback.TimeoutCallback;
import io.yugoal.lib_network.base.NetworkApi;


/**
 * @user caoyu
 * @date 2019/11/13
 */
public class TodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtil.init(this);
        NetworkApi.init(new NetworkRequestInfo(this));
        ToastUtil.init(this);

        if (BuildConfig.DEBUG) {
            // 调试模式开启，如果在install run模式下运行，则必须开启调试模式
            ARouter.openDebug();
            // 日志开启
            ARouter.openLog();
        }
        ARouter.init(this);
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
