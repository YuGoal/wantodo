package io.yugoal.tree.api;

import java.util.List;

import io.reactivex.Observable;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.tree.beans.Tree2Bean;
import io.yugoal.tree.beans.TreeBean;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * user caoyu
 * date 2020/11/18
 * time 17:06
 */
public interface TreeApiInterface {

    //体系
    @GET("tree/json")
    Observable<Response<BaseResponse<List<TreeBean>>>> tree();

    //体系2
    @GET("article/list/{page}/json")
    Observable<Response<BaseResponse<Tree2Bean>>> tree2(@Path("page") int page, @Query("cid") int cid);

}
