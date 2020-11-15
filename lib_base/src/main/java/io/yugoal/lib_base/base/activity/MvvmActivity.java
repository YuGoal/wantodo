package io.yugoal.lib_base.base.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import io.yugoal.lib_base.base.viewmodel.IMvvmBaseViewModel;

public abstract class MvvmActivity<V extends ViewDataBinding,VM extends IMvvmBaseViewModel> extends AppCompatActivity {

    protected VM viewModel;
    protected V viewDataBinding;

    public abstract
    @LayoutRes
    int getLayoutId();

    protected abstract VM getViewModel();
    public abstract int getBindingVariable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
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
}
