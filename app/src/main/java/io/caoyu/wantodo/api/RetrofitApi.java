package io.caoyu.wantodo.api;

import java.util.List;

import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.api.bean.CoinBean;
import io.caoyu.wantodo.api.bean.LoginBean;
import io.caoyu.wantodo.api.bean.Tree2Bean;
import io.caoyu.wantodo.api.bean.TreeBean;
import io.caoyu.wantodo.api.bean.WendaBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ${LiuTao}.
 * User: Administrator
 * Name: ArouteDemo
 * functiona:
 */
public interface RetrofitApi {
/*

    //带参数文件上传
    @Multipart
    @POST("fileController/uploadFile")
    Observable<String> uploadImageFile(@Query("type") int type, @Part List<MultipartBody.Part> files);


    //首页文章列表
    @GET("article/list/{page}/json")
    Observable<Response<ResultBean<ArticleBean>>> article(@Path("page") int page);

    //问答列表
    @GET("wenda/list/{page}/json")
    Observable<Response<ResultBean<WendaBean>>> wenda(@Path("page") int page);

    //体系
    @GET("tree/json")
    Observable<Response<ResultBean<List<TreeBean>>>> tree();

    //体系2
    @GET("article/list/{page}/json")
    Observable<Response<ResultBean<Tree2Bean>>> tree2(@Path("page") int page,@Query("cid") int cid);

    //登录
    @POST("user/login")
    Observable<Response<ResultBean<LoginBean>>> login(@Query("username") String username, @Query("password") String password);

    //个人积分
    @GET("lg/coin/userinfo/json")
    Observable<Response<ResultBean<CoinBean>>> coinCount();
*/


}
