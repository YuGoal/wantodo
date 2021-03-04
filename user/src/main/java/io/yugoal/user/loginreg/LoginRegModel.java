package io.yugoal.user.loginreg;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.model.MvvmBaseModel;
import io.yugoal.lib_base.base.model.PagingResult;
import io.yugoal.lib_network.WanTodoApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.observer.BaseObserver;
import io.yugoal.user.api.LoginRegApiInterface;
import io.yugoal.user.beans.User;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * user caoyu
 * date 2021/3/4
 * time 14:49
 */
public class LoginRegModel extends MvvmBaseModel<ArrayList<BaseCustomViewModel>> {

    public LoginRegModel() {
        super(false, null, null);
    }

    @Override
    public Type getTClass() {
        return new TypeToken<ArrayList<User>>() {
        }.getType();
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        WanTodoApi.getService(LoginRegApiInterface.class).login(formBody)
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<User>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<User>> baseResponseResponse) {
                        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
                        if (baseResponseResponse.isSuccessful()) {
                            if (baseResponseResponse.body().errorCode == 0) {
                                User user = baseResponseResponse.body().data;
                                baseViewModels.add(user);
                            }
                            loadSuccess(baseViewModels, new PagingResult(true, isRefresh, false));
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        loadFail(e.getMessage(), new PagingResult(true, isRefresh, false));
                    }
                }));
    }


    public void reg() {

    }

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {

    }
}
