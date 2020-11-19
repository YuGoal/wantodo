package io.yugoal.lib_base.base.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import io.yugoal.lib_base.R;
import io.yugoal.lib_utils.utils.ToastUtil;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;
import io.yugoal.lib_base.base.viewmodel.ViewStatus;
import io.yugoal.lib_base.loadsir.callback.EmptyCallback;
import io.yugoal.lib_base.loadsir.callback.ErrorCallback;
import io.yugoal.lib_base.loadsir.callback.LoadingCallback;

public abstract class MvvmActivity<V extends ViewDataBinding,VM extends MvvmBaseViewModel> extends AppCompatActivity implements Observer {

    protected VM viewModel;
    private LoadService mLoadService;
    protected V viewDataBinding;

    public abstract
    @LayoutRes
    int getLayoutId();

    protected abstract VM getViewModel();
    public abstract int getBindingVariable();
    protected abstract void onRetryBtnClick();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        performDataBinding();
        if(viewModel != null)
            getLifecycle().addObserver(viewModel);
    }

    private void initViewModel() {
        viewModel = getViewModel();
    }

    private void performDataBinding(){
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        if (viewModel == null){
            viewModel = getViewModel();
        }
        if (getBindingVariable()>0){
            viewDataBinding.setVariable(getLayoutId(),viewModel);
        }
    }

    public void setLoadSir(View view) {
        // You can change the callback on sub thread directly.
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    @Override
    public void onChanged(Object o) {
        if (o instanceof ViewStatus&&mLoadService!=null){
            switch ((ViewStatus)o){
                case LOADING:
                    mLoadService.showCallback(LoadingCallback.class);
                    break;
                case EMPTY:
                    mLoadService.showCallback(EmptyCallback.class);
                    break;
                case NO_MORE_DATA:
                    ToastUtil.show(getString(R.string.base_brvah_load_end));
                    break;
                case SHOW_CONTENT:
                    mLoadService.showSuccess();
                    break;
                case REFRESH_ERROR:
                    if (((ObservableArrayList) viewModel.dataList.getValue()).size() == 0) {
                        mLoadService.showCallback(ErrorCallback.class);
                    } else {
                        ToastUtil.show(viewModel.errorMessage.getValue().toString());
                    }
                    break;
                case LOAD_MORE_FAILED:
                    ToastUtil.show(viewModel.errorMessage.getValue().toString());
                    break;
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(getActivityTag(), "Activity:" + this + ": " + "onDestroy");
    }

    protected String getActivityTag() {
        return this.getClass().getSimpleName();
    }
}
