package io.yugoal.user;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yugoal.lib_common_ui.arouter.IUserService;
import io.yugoal.user.loginreg.LoginActivity;

/**
 * user caoyu
 * date 2021/3/4
 * time 14:22
 */
@Route(path = IUserService.USER_SERVICE)
public class UserService implements IUserService {
    private Context mContext;

    @Override
    public void init(Context context) {
        mContext = context;
    }

    @Override
    public void showLogin() {
        LoginActivity.show(mContext);
    }

    @Override
    public void showReg() {

    }
}
