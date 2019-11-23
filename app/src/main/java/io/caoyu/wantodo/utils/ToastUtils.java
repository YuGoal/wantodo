package io.caoyu.wantodo.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import io.caoyu.wantodo.application.TodoApplication;


/**
 * * @author ${LiuTao}
 *
 * @date 2018/3/17/017
 */

public class ToastUtils {
    private static Toast toast;
    private static Handler mUIHandler = new Handler(Looper.getMainLooper());

    public static void showToast(final String msg) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(TodoApplication.getInstance().getApplicationContext(), msg + "", Toast.LENGTH_SHORT);
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
                toast = Toast.makeText(TodoApplication.getInstance().getApplicationContext(), resId, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setText(resId);
                toast.show();
            }
        });
    }

    public static void showToast(final int resId, boolean append) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(TodoApplication.getInstance().getApplicationContext(), resId + "", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setText(resId);
                toast.show();
            }
        });
    }

    public static void setResultToToast(final String string) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TodoApplication.getInstance().getApplicationContext(), string + "", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void setResultToText(final TextView tv, final String s) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                if (tv == null) {
                    // Toast.makeText(FPVDemoApplication.context, "tv is null", Toast.LENGTH_LONG).show();
                } else {
                    tv.setText(s);
                }
            }
        });
    }

    public static void showLongToast(final String msg) {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                toast = Toast.makeText(TodoApplication.getInstance().getApplicationContext(), msg + "", Toast.LENGTH_LONG);
                toast.setText(msg + "");
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

}
