package io.caoyu.wantodo.model.user;

import io.caoyu.wantodo.model.BaseModel;

/**
 * @user caoyu
 * @date 2019/11/23
 */
public class BaseUserModel extends BaseModel {
    private int code;
    private User data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
