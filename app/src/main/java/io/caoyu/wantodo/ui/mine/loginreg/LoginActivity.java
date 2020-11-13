package io.caoyu.wantodo.ui.mine.loginreg;

import android.os.Bundle;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.FragmentLoginBinding;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;

public class LoginActivity extends BaseDataBindActivity<FragmentLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }
}