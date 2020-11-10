package io.caoyu.wantodo.api;

import java.util.List;

import io.caoyu.wantodo.api.bean.ArticleBean;
import io.reactivex.Observable;
import io.yugoal.lib_network.okhttp.ResultBean;
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

    //带参数文件上传
    @Multipart
    @POST("fileController/uploadFile")
    Observable<String> uploadImageFile(@Query("type") int type, @Part List<MultipartBody.Part> files);


    //首页文章列表
    @GET("article/list/{page}/json")
    Observable<Response<ResultBean<ArticleBean>>> article(@Path("page") int page);


}
