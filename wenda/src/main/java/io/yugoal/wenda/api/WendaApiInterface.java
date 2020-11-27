package io.yugoal.wenda.api;

import io.reactivex.Observable;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.wenda.beans.WendaBean;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * user caoyu
 * date 2020/11/18
 * time 17:06
 */
public interface WendaApiInterface {

    //问答列表
    @GET("wenda/list/{page}/json")
    Observable<Response<BaseResponse<WendaBean>>> wenda(@Path("page") int page);

}
