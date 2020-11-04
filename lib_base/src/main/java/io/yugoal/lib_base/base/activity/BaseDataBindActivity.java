package io.yugoal.lib_base.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import io.yugoal.lib_base.base.activity.BaseActivity;

/**
 * user caoyu
 * date 2020/10/30
 * time 10:54
 */
public abstract class BaseDataBindActivity<V extends ViewDataBinding> extends BaseActivity {
    protected V dataBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind = DataBindingUtil.setContentView(this,getLayoutId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBind != null) {
            dataBind.unbind();
            dataBind = null;
        }
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();
}
