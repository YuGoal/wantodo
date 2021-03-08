package io.yugoal.article.api;

import io.reactivex.Observable;
import io.yugoal.article.beans.ArticleBean;
import io.yugoal.lib_network.beans.BaseResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * user caoyu
 * date 2020/11/18
 * time 17:06
 */
public interface ArticleApiInterface {

    //首页文章列表
    @GET("article/list/{page}/json")
    Observable<Response<BaseResponse<ArticleBean>>> article(@Path("page") int page);

    //收藏文章列表
    @GET("lg/collect/list/{page}/json")
    Observable<Response<BaseResponse<ArticleBean>>> collect(@Path("page") int page);

}
