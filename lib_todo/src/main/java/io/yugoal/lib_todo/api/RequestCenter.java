package io.yugoal.lib_todo.api;

import io.yugoal.lib_network.okhttp.CommonOkHttpClient;
import io.yugoal.lib_network.okhttp.listener.DisposeDataHandle;
import io.yugoal.lib_network.okhttp.listener.DisposeDataListener;
import io.yugoal.lib_network.okhttp.request.CommonRequest;
import io.yugoal.lib_network.okhttp.request.RequestParams;

/**
 * @user caoyu
 * @date 2019/11/14
 */
public class RequestCenter {
    static class HttpConstants {
        private static final String ROOT_URL = "https://www.wanandroid.com";
        //private static final String ROOT_URL = "http://39.97.122.129";



        /**
         * 登录
         */
        public static String LOGIN = ROOT_URL + "/user/login";

    }

    //根据参数发送所有post请求
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 用户登录请求
     */
    public static void login(DisposeDataListener listener, RequestParams params) {
//        RequestCenter.getRequest(HttpConstants.LOGIN, params, listener, User.class);
    }

}
