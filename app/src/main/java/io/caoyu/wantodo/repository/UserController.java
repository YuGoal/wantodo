package io.caoyu.wantodo.repository;

import android.text.TextUtils;

import io.caoyu.wantodo.api.RequestCenter;
import io.caoyu.wantodo.constant.Constant;
import io.caoyu.wantodo.model.user.BaseUserModel;
import io.caoyu.wantodo.model.user.User;
import io.caoyu.wantodo.utils.ToastUtils;
import io.yugoal.lib_common_ui.utils.SpUtils;
import io.yugoal.lib_network.okhttp.listener.DisposeDataListener;
import io.yugoal.lib_network.okhttp.request.RequestParams;

/**
 * @user caoyu
 * @date 2019/11/23
 */
public class UserController {
    public static UserController getInstance() {
        return UserController.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static UserController instance = new UserController();
    }

    public void login() {
        String username, pwd;
        username = SpUtils.getValue(Constant.NICKNAME, "");
        pwd = SpUtils.getValue(Constant.PWD, "");
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
        } else {
            RequestParams params = new RequestParams();
            params.put("username", username);
            params.put("password", pwd);
            RequestCenter.login(params, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    BaseUserModel baseUserModel = (BaseUserModel) responseObj;
                    if (baseUserModel.getCode() == 0) {
                        User user = baseUserModel.getData();
                        SpUtils.setValue(Constant.NICKNAME, user.getNickname());
                        SpUtils.setValue(Constant.PWD, user.getPassword());
                        SpUtils.setValue(Constant.IS_LOGIN, true);
                    } else {
                        ToastUtils.showToast(baseUserModel.getMsg());
                    }
                }

                @Override
                public void onFailure(Object reasonObj) {
                }
            });
        }
    }

    public void logout() {
    }


    public void register(String username, String password, String repassword) {
    }
}
