package io.yugoal.lib_network;

import java.io.IOException;

import io.reactivex.functions.Function;
import io.yugoal.lib_network.base.NetworkApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.errorhandler.ExceptionHandle;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * user caoyu
 * date 2020/11/18
 * time 16:12
 */
public class WanTodoApi extends NetworkApi {
    private static volatile WanTodoApi singleton = null;

    private WanTodoApi() {}

    public static WanTodoApi getInstance() {
        if (singleton == null) {
            synchronized (WanTodoApi.class) {
                if (singleton == null) {
                    singleton = new WanTodoApi();
                }
            }
        }
        return singleton;
    }

    public static  <T> T getService(Class<T> service) {
        return getInstance().getRetrofit(service).create(service);
    }


    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
   /*             builder.addHeader("Source", "source");
                builder.addHeader("Authorization", TecentUtil.getAuthorization(timeStr));
                builder.addHeader("Date", timeStr);*/
                return chain.proceed(builder.build());
            }
        };
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
                if (response instanceof BaseResponse && ((BaseResponse) response).errorCode != 0) {
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((BaseResponse) response).errorCode;
                    exception.message = ((BaseResponse) response).errorMsg != null ? ((BaseResponse) response).errorMsg : "";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    public String getFormal() {
        return null;
    }

    @Override
    public String getTest() {
        return null;
    }
}
