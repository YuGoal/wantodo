package io.caoyu.wantodo.ui.mine.loginreg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.Constants;
import io.caoyu.wantodo.api.RetrofitClient;
import io.caoyu.wantodo.api.bean.LoginBean;
import io.caoyu.wantodo.databinding.FragmentLoginBinding;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;
import io.yugoal.lib_network.okhttp.ResultBean;
import io.yugoal.lib_network.okhttp.RxObservableTransformer;
import io.yugoal.lib_base.SPUtils;
import io.yugoal.lib_utils.utils.StringUtils;
import io.yugoal.lib_utils.utils.ToastUtils;
import retrofit2.Response;

public class LoginActivity extends BaseDataBindActivity<FragmentLoginBinding> {


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

    @Override
    public void initViewModel() {

    }

    private void initEvent() {
        dataBind.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = dataBind.etUsername.getText().toString();
                String password = dataBind.etPwd.getText().toString();
                //验证用户名和密码
                if(validateAccount(account)&&validatePassword(password)){
                    login(account,password);
                }
            }
        });
    }

    private void login(String account, String password) {
        showDialogLoading("登录中...");
        RetrofitClient.getRetrofit().getRetrofitApi()
                .login(account,password)
                .compose(RxObservableTransformer.transformer())
                .subscribe(new Observer<Response<ResultBean<LoginBean>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResultBean<LoginBean>> resultBeanResponse) {
                        dismissDialogLoading();
                        if (resultBeanResponse.isSuccessful()){
                            if (resultBeanResponse.body().getErrorCode() == 0){
                                ToastUtils.showToast("登录成功");
                                SPUtils.putString(Constants.NAME,resultBeanResponse.body().getData().getNickname());
                                SPUtils.putInt(Constants.ID,resultBeanResponse.body().getData().getId());
                                finish();
                            }else {
                                ToastUtils.showToast(resultBeanResponse.body().getErrorMsg());
                            }

                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputEditText textInputLayout, String error){
        textInputLayout.setError(error);
    }

    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(StringUtils.isEmpty(account)){
            showError(dataBind.etUsername,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(dataBind.etPwd,"密码不能为空");
            return false;
        }

        return true;
    }
}