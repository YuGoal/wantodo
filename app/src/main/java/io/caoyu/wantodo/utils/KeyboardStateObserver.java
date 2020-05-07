package io.caoyu.wantodo.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.LogUtils;

/**
 * yugoal
 * 2020-01-22
 * 14:15
 */
public class KeyboardStateObserver {

    private static final String TAG = KeyboardStateObserver.class.getSimpleName();

    public static KeyboardStateObserver getKeyboardStateObserver(Activity activity) {
        return new KeyboardStateObserver(activity);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private OnKeyboardVisibilityListener listener;

    public void setKeyboardVisibilityListener(OnKeyboardVisibilityListener listener) {
        this.listener = listener;
    }

    private KeyboardStateObserver(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                if (listener != null) {
                    listener.onKeyboardShow(heightDifference);
                }
            } else {
                if (listener != null) {
                    listener.onKeyboardHide();
                }
            }
            usableHeightPrevious = usableHeightNow;
            LogUtils.d(TAG,"usableHeightNow: " + usableHeightNow + " | usableHeightSansKeyboard:" +
                    usableHeightSansKeyboard + " | heightDifference:" + heightDifference);
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        LogUtils.d(TAG,"rec bottom>" + r.bottom + " | rec top>" + r.top);
        return (r.bottom - r.top);// 全屏模式下： return r.bottom
    }

    public interface OnKeyboardVisibilityListener {
        void onKeyboardShow(int heightDifference);

        void onKeyboardHide();
    }
}
