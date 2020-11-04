package io.yugoal.lib_utils.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * * @author ${LiuTao}
 *
 * @date 2018/3/17/017
 */

public class ToastUtils {
    private static Toast toast;
    private static Handler mUIHandler = new Handler(Looper.getMainLooper());

    public static void showToast(final String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), msg + "", Toast.LENGTH_SHORT);
                toast.setText(msg + "");
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static void showToast(final int resId) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), resId , Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static void showToast(final int resId, boolean append) {
        if (TextUtils.isEmpty(resId + "")) {
            return;
        }
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), resId + "", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static void showLongToast(final String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), msg + "", Toast.LENGTH_SHORT);
                toast.setText(msg + "");
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static void showLongToast(final int resId) {
        if (TextUtils.isEmpty(resId + "")) {
            return;
        }

        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), resId + "", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    public static void showLongToast(final int resId, boolean append) {
        if (TextUtils.isEmpty(resId + "")) {
            return;
        }
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), resId + "", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    //注意:该方法创建Toast对象的时候时长因该设置为 Toast.LENGTH_LONG,因为该他的时长就是3秒,与下面的延时时间对应
//cnt:需要显示的时长,毫秒
    public static void showCustomTimeToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);//每隔三秒调用一次show方法;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt);//经过多长时间关闭该任务
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     */
    private static long mExitTime;

    //===========================================Toast 替代方法======================================

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg      要显示的字符串
     * @param duration 显示时长
     */
    @SuppressLint("ShowToast")
    public static void showToast(final String msg, final int duration) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(Utils.getApp(), msg + "", duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

    }

    public static boolean doubleClickExit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showToast("再按一次退出");
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }
}
