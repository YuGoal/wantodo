package io.yugoal.user.loginreg;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;

/**
 * user caoyu
 * date 2021/3/4
 * time 14:44
 */
public class LoginViewModel extends MvvmBaseViewModel<LoginRegModel, BaseCustomViewModel> {
    public LoginViewModel init() {
        model = new LoginRegModel();
        model.register(this);
        return this;
    }

    public void login(String username, String password){
        model.login(username,password);
    }
}
