package io.yugoal.lib_common_ui.arouter;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * user caoyu
 * date 2021/3/4
 * time 14:23
 */
public interface IUserService extends IProvider {
    String USER_ROUTER = "/user/";
    String USER_SERVICE = USER_ROUTER + "user_service";

    void showLogin();

    void showReg();
    void showRank();
    void showSet();

}