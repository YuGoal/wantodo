package io.caoyu.wantodo.application;

import android.content.Context;
import android.os.Process;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kingja.loadsir.core.LoadSir;
import com.tencent.bugly.Bugly;

import io.caoyu.wantodo.BuglyUtils;
import io.caoyu.wantodo.BuildConfig;
import io.yugoal.lib_base.BaseApp;
import io.yugoal.lib_base.loadsir.callback.CustomCallback;
import io.yugoal.lib_base.loadsir.callback.EmptyCallback;
import io.yugoal.lib_base.loadsir.callback.ErrorCallback;
import io.yugoal.lib_base.loadsir.callback.LoadingCallback;
import io.yugoal.lib_base.loadsir.callback.TimeoutCallback;
import io.yugoal.lib_network.base.NetworkApi;
import io.yugoal.lib_utils.utils.ToastUtil;


/**
 * @user caoyu
 * @date 2019/11/13
 */
public class TodoApplication extends BaseApp {

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        NetworkApi.init(new NetworkRequestInfo(this));
        ToastUtil.init(this);

        if (BuildConfig.DEBUG) {
            // 调试模式开启，如果在install run模式下运行，则必须开启调试模式
            ARouter.openDebug();
            // 日志开启
            ARouter.openLog();
        }
        ARouter.init(this);
        initBugly();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }

    /**
     * 初始化Bugly
     */
    private void initBugly() {
        /*全量更新配置*/
        //BuglyUtils.BuglyAppConfig();
        /*热更新配置 还未集成*/
        //BuglyUtils.BuglyHotConfig();
        Bugly.init(getApplicationContext(), "efbaaa61a0", BuildConfig.DEBUG);
        BuglyUtils.BuglyAppConfig();
    }

    public static void exitApp() {
        Process.killProcess(Process.myPid());
    }
}
