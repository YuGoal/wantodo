package io.caoyu.wantodo.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.caoyu.wantodo.application.TodoApplication;
import io.yugoal.lib_base.App;
import io.yugoal.lib_network.okhttp.NetApi;
import io.yugoal.lib_network.okhttp.TInterceptor;
import io.yugoal.lib_network.okhttp.cook.CookieJarImpl;
import io.yugoal.lib_network.okhttp.cook.SPCookieStore;
import io.yugoal.lib_utils.utils.JsonUtils;
import io.yugoal.lib_utils.utils.NetworkUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 0;
    private static RetrofitClient mInstance;
    private RetrofitApi mRetrofitApi;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private final String CACHE_NAME = "";
    private static RetrofitClient instance = new RetrofitClient();

    public static RetrofitClient getRetrofit() {
        return instance;
    }

    private RetrofitClient() {

    }

    /**
     * rxjava+返回javabean
     * @return
     */
    public RetrofitApi getRetrofitApi() {
        /**
         * 设置缓存
         */
        File cacheFile = new File(TodoApplication.getInstance().getExternalCacheDir(), CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {//无网络
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0;  //有网络不会使用缓存数据,直接获取网络数据
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)//固定写法
                            .removeHeader(CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，使用缓存设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)//固定写法
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                return response;
            }
        };

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        // 初始化 OkHttpClient
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时
                .readTimeout(10, TimeUnit.SECONDS)//读取超时
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(cacheInterceptor)//缓存
                .addInterceptor(headerInterceptor)
                .cookieJar(new CookieJarImpl(new SPCookieStore(App.getContext()))) //使用sp保持cookie，如果cookie不过期，则一直有效
                .addNetworkInterceptor(new TInterceptor())// 加入日志拦截
                .build();
        mRetrofit = getRetrofitJavaBeanRxjava(NetApi.BASEURL);
        mRetrofitApi = mRetrofit.create(RetrofitApi.class);
        return mRetrofitApi;
    }

    /**
     * 回调String字符串数据
     *
     * @return
     */
    public RetrofitApi getRetrofitApiString() {
        /**
         * 设置缓存
         */
        File cacheFile = new File(App.getInstance().getExternalCacheDir(), CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {//无网络
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 0;  //有网络不会使用缓存数据,直接获取网络数据
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)//固定写法
                            .removeHeader(CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，使用缓存设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)//固定写法
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                return response;
            }
        };


        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        // 初始化 OkHttpClient
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时
                .readTimeout(10, TimeUnit.SECONDS)//读取超时
                .writeTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(cacheInterceptor)//缓存
                .addInterceptor(headerInterceptor)
                .cookieJar(new CookieJarImpl(new SPCookieStore(TodoApplication.getContext()))) //使用sp保持cookie，如果cookie不过期，则一直有效
                .addNetworkInterceptor(new TInterceptor())// 加入日志拦截
                .build();
        mRetrofit = getRetrofitBuildStringRxjava();
        mRetrofitApi = mRetrofit.create(RetrofitApi.class);
        return mRetrofitApi;
    }

    /**
     * Rxjava支持+返回JavaBen类型
     *
     * @param baseIpSky
     */
    private Retrofit getRetrofitJavaBeanRxjava(String baseIpSky) {
        return new Retrofit.Builder()
                .baseUrl(baseIpSky)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(JsonUtils.getGson()))////添加Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//  添加 RxJava 支持 能更优雅的取消请求
                .build();

    }

    /**
     * Rxjava支持+返回String类型
     */
    private Retrofit getRetrofitBuildStringRxjava() {
        return new Retrofit.Builder()
                .baseUrl(NetApi.BASEURL)
                .client(mOkHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())////添加String转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//  添加 RxJava 支持 能更优雅的取消请求
                .build();

    }

    /**
     * 变更
     *
     * @param baseUrl
     * @return
     */
    private RetrofitClient changeBaseUrl(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        mRetrofitApi = mRetrofit.create(RetrofitApi.class);
        return mInstance;
    }

    //单个文件
    public static MultipartBody.Part getMultipartBodyPart(String name, File file) {
        RequestBody imageBody = RequestBody.create(MultipartBody.FORM, file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData(name, file.getAbsolutePath(), imageBody);
        return imageBodyPart;
    }

    //多个文件
    public static List<MultipartBody.Part> getMultipartBodyParts(String name, List<File> files) {
//        MediaType fileType = MediaType.parse("application/octet-stream");
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody imageBody = RequestBody.create(MultipartBody.FORM, file);
            MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData(name, file.getAbsolutePath(), imageBody);
            parts.add(imageBodyPart);
        }
        return parts;
    }
}