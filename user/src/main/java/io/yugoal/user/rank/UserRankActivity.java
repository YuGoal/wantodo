package io.yugoal.user.rank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_network.WanTodoApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.observer.BaseObserver;
import io.yugoal.lib_utils.utils.ToastUtil;
import io.yugoal.user.R;
import io.yugoal.user.api.LoginRegApiInterface;
import io.yugoal.user.beans.CoinBean;
import io.yugoal.user.databinding.ActivityUserRankBinding;
import io.yugoal.user.loginreg.LoginViewModel;
import retrofit2.Response;

public class UserRankActivity extends MvvmActivity<ActivityUserRankBinding, LoginViewModel> {

    public static void show(Context context) {
        Intent intent = new Intent(context, UserRankActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("个人积分");
    }

    @Override
    public void onResume() {
        super.onResume();
        getRank();
    }

    private void getRank() {

        WanTodoApi.getService(LoginRegApiInterface.class).coin()
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<CoinBean>>>(null) {
                    @Override
                    public void onSuccess(Response<BaseResponse<CoinBean>> baseResponseResponse) {
                        dismissLoading();
                        if (baseResponseResponse.isSuccessful()) {
                            if (baseResponseResponse.body().errorCode == 0) {
                                CoinBean coinBean = baseResponseResponse.body().data;
                                viewDataBinding.tvName.setText(coinBean.getUsername());
                                viewDataBinding.tvCoinCount.setText(String.valueOf(coinBean.getCoinCount()));
                                viewDataBinding.tvRank.setText(String.valueOf(coinBean.getRank()));
                                viewDataBinding.tvLevel.setText(String.valueOf(coinBean.getLevel()));
                            } else {
                                ToastUtil.show(baseResponseResponse.body().errorMsg);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        dismissLoading();
                        ToastUtil.show(e.getMessage());
                    }
                }));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_rank;
    }

    @Override
    protected LoginViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}
