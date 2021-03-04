package io.yugoal.lib_network.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * user caoyu
 * date 2020/11/18
 * time 16:15
 */
public class BaseResponse<T> {
    @SerializedName("errorCode")
    @Expose
    public Integer errorCode;
    @SerializedName("errorMsg")
    @Expose
    public String errorMsg;
    @SerializedName("data")
    @Expose
    public T data;

}
