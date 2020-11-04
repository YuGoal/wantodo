package io.yugoal.lib_base.base.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_base.R;

/**
 * user caoyu
 * date 2020/10/21
 * time 14:27
 */
public class BaseDialogFragment extends DialogFragment {
    private ViewModelProvider viewModelProvider;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全透明
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.NoBackGroundDialog);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window win = Objects.requireNonNull(getDialog()).getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        assert win != null;
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowParams = win.getAttributes();
        windowParams.dimAmount = 0.0f;//Dialog外边框透明

        DisplayMetrics dm = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.CENTER;
        // 判断Android当前的屏幕是横屏还是竖屏。横竖屏判断
        /*if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            params.width = (int) (dm.widthPixels * 0.9);
            params.height = (int) (dm.heightPixels * 0.7);
        } else {
            //横屏
            params.width = (int) (dm.widthPixels * 0.7);
            params.height = (int) (dm.heightPixels * 0.9);
        }*/
        win.setAttributes(params);
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

    private CompositeDisposable compositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dispose();
    }


    /**
     * onDestroy里面调用
     */
    public void dispose() {
        if (compositeDisposable != null
                && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose(); //解除订阅
            compositeDisposable.clear(); //取消所有订阅
            compositeDisposable = null;
        }
    }
}
