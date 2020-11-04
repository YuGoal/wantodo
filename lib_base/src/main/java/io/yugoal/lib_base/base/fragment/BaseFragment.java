package io.yugoal.lib_base.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_base.App;
import io.yugoal.lib_base.base.viewmodel.SharedViewModel;
import io.yugoal.lib_common_ui.XToolbar;
import io.yugoal.lib_utils.utils.StatusBarUtils;


/**
 * @ClassName: BaseFragment
 * @Description:
 * @Author: LiuTao
 * @CreateDate: 2020/5/9 13:47
 * @UpdateUser: LiuTao
 */
public abstract class BaseFragment extends Fragment {
    private ViewModelProvider viewModelProvider;

    public abstract void initViewModel();

    public abstract void initData();

    private SharedViewModel mSharedViewModel;
    protected AppCompatActivity mActivity;

    private XToolbar mToolbar;


    protected XToolbar getToolbar() {
        return mToolbar;
    }

    public void initToolbar(XToolbar toolbar) {
        mToolbar = toolbar;
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                getActivity().finish();
            }
        });
    }

    public void setToolbarTitle(CharSequence title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }

    }

    public void setInflateMenu(@MenuRes int resId) {
        mToolbar.inflateMenu(resId);
    }

    public void setInflateMenu(@MenuRes int resId, Toolbar.OnMenuItemClickListener itemClickListener) {
        if (mToolbar != null) {
            mToolbar.inflateMenu(resId);
            mToolbar.setOnMenuItemClickListener(itemClickListener);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ARouter.getInstance().inject(this);
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 创建ViewModel对象
     *
     * @param modelClass
     * @return
     */
    protected <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(mActivity);
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
        return new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()));
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

    public void setAdapterStatusBar(View view) {
        if (view.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = StatusBarUtils.getStatusBarHeight();
            view.setLayoutParams(layoutParams);
        } else if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = StatusBarUtils.getStatusBarHeight();
            view.setLayoutParams(layoutParams);
        } else if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = StatusBarUtils.getStatusBarHeight();
            view.setLayoutParams(layoutParams);
        }
    }


}
