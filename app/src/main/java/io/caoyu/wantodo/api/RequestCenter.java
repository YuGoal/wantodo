package io.caoyu.wantodo.api;

import io.caoyu.wantodo.model.todo.BaseToDoListModel;
import io.caoyu.wantodo.model.user.BaseUserModel;
import io.yugoal.lib_network.okhttp.CommonOkHttpClient;
import io.yugoal.lib_network.okhttp.listener.DisposeDataHandle;
import io.yugoal.lib_network.okhttp.listener.DisposeDataListener;
import io.yugoal.lib_network.okhttp.request.CommonRequest;
import io.yugoal.lib_network.okhttp.request.RequestParams;

/**
 * @user caoyu
 * @date 2019/11/23
 */
public class RequestCenter {

    static class HttpConstants {
        private static final String ROOT_URL = "https://www.wanandroid.com";

        /**
         * TODO列表
         */
        private static String LIST = ROOT_URL + "/lg/todo/v2/list";

        /**
         * 新增
         */
        private static String ADD = ROOT_URL + "/lg/todo/add/json";
        /**
         * 更新
         */
        private static String UPDATE = ROOT_URL + "/lg/todo/update/83/json";
        /**
         * 删除
         */
        private static String DELETE = ROOT_URL + "/lg/todo/delete/83/json";
        /**
         * 完成
         */
        private static String DONE = ROOT_URL + "/lg/todo/done/80/json";

        /**
         * 登陆接口
         */
        public static String LOGIN = ROOT_URL + "/user/login";
        /**
         * 注册
         */
        public static String REGISTER = ROOT_URL + "/user/register";
        /**
         * 登出
         */
        public static String LOGOUT = ROOT_URL + "/user/logout/json";
    }

    //根据参数发送所有get请求
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener,
                                  Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener,
                                   Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    //根据参数发送所有downloadFile请求
    public static void downloadFile(String url, RequestParams params, DisposeDataListener listener,
                                    Class<?> clazz) {
        CommonOkHttpClient.downloadFile(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 用户登陆请求
     */
    public static  void login(RequestParams params, DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, BaseUserModel.class);
    }

    /**
     * 获取todo列表
     *
     * @param params   参数
     * @param listener 回调
     */
    public static void getList(RequestParams params, DisposeDataListener listener) {

        RequestCenter.getRequest(HttpConstants.LIST, params, listener, BaseToDoListModel.class);
    }
}
