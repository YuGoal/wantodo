package io.yugoal.lib_base.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @ClassName: BaseDataBindFragment
 * @Description:
 * @Author: LiuTao
 * @CreateDate: 2020/5/9 14:17
 * @UpdateUser: LiuTao
 */
public abstract class BaseDataBindFragment<V extends ViewDataBinding> extends BaseFragment {
    protected V dataBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            dataBind = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            initView(savedInstanceState);
            return dataBind.getRoot();
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView(@Nullable Bundle savedInstanceState);


}
