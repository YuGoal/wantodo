package io.yugoal.user;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import io.yugoal.lib_base.base.preference.SPUtils;
import io.yugoal.user.beans.User;
import io.yugoal.user.loginreg.LoginActivity;


/**
 * @author CuiZhen
 * @date 2019/5/15
 * GitHub: https://github.com/goweii
 */
public class UserUtils {

    private static final String KEY_LOGIN_BEAN = "KEY_LOGIN_BEAN";

    private User mLoginBean = null;

    private static class Holder {
        private static final UserUtils INSTANCE = new UserUtils();
    }

    public static UserUtils getInstance() {
        return Holder.INSTANCE;
    }

    private UserUtils() {
        getLoginBean();
    }

    public User getLoginBean() {
        if (mLoginBean == null) {
            String json = SPUtils.getInstance().get(KEY_LOGIN_BEAN, "");
            if (!TextUtils.isEmpty(json)) {
                try {
                    mLoginBean = new Gson().fromJson(json, User.class);
                } catch (Exception ignore) {
                }
            }
        }
        return mLoginBean;
    }

    public void login(User loginBean) {
        mLoginBean = loginBean;
        String json = new Gson().toJson(loginBean);
        SPUtils.getInstance().save(KEY_LOGIN_BEAN, json);
    }

    public void logout() {
        mLoginBean = null;
        SPUtils.getInstance().clear();
    }

    public void update(User loginBean) {
        mLoginBean = loginBean;
        SPUtils.getInstance().save(KEY_LOGIN_BEAN, mLoginBean);
    }

    public boolean isLogin() {
        User loginBean = getLoginBean();
        if (loginBean == null) {
            return false;
        }
        if (loginBean.getId() > 0) {
            return true;
        }
        return false;
    }

    public int getUserId() {
        User loginBean = getLoginBean();
        if (loginBean == null) {
            return 0;
        }
        return loginBean.getId();
    }

    public String getNickname() {
        User loginBean = getLoginBean();
        if (loginBean == null) {
            return "";
        }
        return loginBean.getNickname();
    }


    public boolean doIfLogin(Context context) {
        if (isLogin()) {
            return true;
        } else {
            LoginActivity.show(context);
            return false;
        }
    }

}
