package io.caoyu.wantodo.view.mine.loginreg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.FragmentRegBinding;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;

/**
 * user caoyu
 * date 2020/11/10
 * time 14:35
 * 注册
 */
public class RegActivity extends BaseDataBindActivity<FragmentRegBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reg;
    }
}