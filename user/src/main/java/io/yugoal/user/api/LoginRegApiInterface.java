package io.yugoal.user.api;

import io.reactivex.Observable;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.user.beans.User;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * user caoyu
 * date 2021/3/4
 * time 14:23
 */
public interface LoginRegApiInterface {
    //登录
    @POST("user/login")
    Observable<Response<BaseResponse<User>>> login(@Body RequestBody route);
}
