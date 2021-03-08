package io.yugoal.user.loginreg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_base.base.preference.SPUtils;
import io.yugoal.lib_network.WanTodoApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.observer.BaseObserver;
import io.yugoal.lib_utils.utils.StringUtils;
import io.yugoal.lib_utils.utils.ToastUtil;
import io.yugoal.user.R;
import io.yugoal.user.UserUtils;
import io.yugoal.user.api.Constants;
import io.yugoal.user.api.LoginRegApiInterface;
import io.yugoal.user.beans.User;
import io.yugoal.user.databinding.FragmentLoginBinding;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class LoginActivity extends MvvmActivity<FragmentLoginBinding, LoginViewModel> {
    private static final String TAG = "LoginActivity";

    public static void show(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("登录");
        initEvent();
    }

    private void initEvent() {
        viewDataBinding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = viewDataBinding.etUsername.getText().toString();
                String password = viewDataBinding.etPwd.getText().toString();
                //验证用户名和密码
                if (validateAccount(account) && validatePassword(password)) {
                    showPDLoading("登录中...");
                    login(account, password);
                }
            }
        });


    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        WanTodoApi.getService(LoginRegApiInterface.class).login(formBody)
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<User>>>(null) {
                    @Override
                    public void onSuccess(Response<BaseResponse<User>> baseResponseResponse) {
                        dismissLoading();
                        if (baseResponseResponse.isSuccessful()) {
                            if (baseResponseResponse.body().errorCode == 0) {
                                User user = baseResponseResponse.body().data;
                                UserUtils.getInstance().login(user);
                                finish();
                            }else {
                                ToastUtil.show(baseResponseResponse.body().errorMsg);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        dismissLoading();
                        ToastUtil.show(e.getMessage());
                    }
                }));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected LoginViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }


    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputEditText textInputLayout, String error) {
        textInputLayout.setError(error);
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            showError(viewDataBinding.etUsername, "用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(viewDataBinding.etPwd, "密码不能为空");
            return false;
        }

        return true;
    }
}