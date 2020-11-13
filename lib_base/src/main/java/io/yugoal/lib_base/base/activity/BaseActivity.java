package io.yugoal.lib_base.base.activity;


import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_base.base.viewmodel.SharedViewModel;
import io.yugoal.lib_common_ui.utils.StatusBarUtil;
import io.yugoal.lib_utils.utils.StatusBarUtils;

/**
 * @author caoyu
 * date  2019/9/5
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ViewModelProvider viewModelProvider;
    private SharedViewModel mSharedViewModel;
    public abstract void initViewModel();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarLightMode(true);
        initViewModel();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 创建ViewModel对象
     *
     * @param modelClass
     * @return
     */
    protected <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(this);
        }
        return viewModelProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getViewModel(ViewModelStoreOwner viewModelStoreOwner, @NonNull Class<T> modelClass) {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(viewModelStoreOwner);
        }
        return viewModelProvider.get(modelClass);
    }

    /**
     * 初始化ViewModelProvider对象
     *
     * @return
     */
    private ViewModelProvider getViewModelProvider() {
        return new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()));
    }


    /**
     * 隐藏状态栏的高度
     */
    public void setStatusBarTrans() {
        //StatusBarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.transparent));
        //侵入状态栏
        StatusBarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0), true);
    }

    /**
     * 隐藏状态栏的高度
     */
    public void setStatusBarTrans(final boolean isDecor) {
        //StatusBarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.transparent));
        //侵入状态栏
        StatusBarUtils.setStatusBarColor(this, Color.argb(0, 0, 0, 0), isDecor);
    }

    public void setStatusBarHide() {
        //侵入状态栏
        StatusBarUtils.setNavBarVisibility(getWindow(), this, false);

    }
    /**
     * 保留状态栏高度,状态栏设置为透明
     */
    public void setStatusBarShowHeight() {
        StatusBarUtils.setStatusBarColor(this, Color.TRANSPARENT);
    }

    /**
     * 保留状态栏高度,状态栏透明
     */
    public void setStatusBarShowHeight(boolean isDecor) {
        StatusBarUtils.setStatusBarColor(this, Color.TRANSPARENT, isDecor);
    }

    /**
     * 保留状态栏高度,状态栏设置颜色
     */
    public void setStatusBarShowHeight(@ColorRes int id) {
        StatusBarUtils.setStatusBarColor(this, ContextCompat.getColor(this, id));
    }

    /**
     * 状态栏黑白模式
     * true 白色 false 黑
     */
    public void setStatusBarLightMode(boolean isLightMode) {
        StatusBarUtils.setStatusBarLightMode(this, isLightMode);
    }

    private ProgressDialog dialog;

    public void showDialogLoading(String msg) {
        try {
            if (dialog != null && dialog.isShowing() && !isFinishing()) return;
            dialog = new ProgressDialog(this);
            dialog.setMessage(msg);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissDialogLoading() {
        if (!isFinishing()) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }


    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }
}
