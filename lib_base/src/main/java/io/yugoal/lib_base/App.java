package io.yugoal.lib_base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.kingja.loadsir.core.LoadSir;

import io.yugoal.lib_utils.utils.Utils;


public class App extends Application implements ViewModelStoreOwner {
    private static App INSTANCE;
    public static String UpdateAppID = "updateApp";
    public static String ChannelName = "程序升级";

    public static String RecordScreenID = "1101";
    public static String RecordScreenChannelName = "RecordScreenChannelName";
    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.Factory mFactory;
    private Handler mainHandler;

    //private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        mainHandler = new Handler(Looper.getMainLooper());
        mAppViewModelStore = new ViewModelStore();
        Utils.init(this);
        initNotificationManager();
//        initRouter(this);
        /*NetworkStateManager.getInstance(this).onRegist(getContext());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
 LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
        mRefWatcher = LeakCanary.install(this);*/

    }

    /*public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.mRefWatcher;
    }*/

    public static App getInstance() {
        return INSTANCE;
    }


    public static Context getContext() {
        return INSTANCE.getApplicationContext();
    }


    private void initNotificationManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(UpdateAppID, ChannelName, importance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }


    private ViewModelProvider viewModelProvider;

    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((App) activity.getApplicationContext(),
                ((App) activity.getApplicationContext()).getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public Handler getMainHandler() {
        return mainHandler;
    }


    public void toLogin(){
    }
}
