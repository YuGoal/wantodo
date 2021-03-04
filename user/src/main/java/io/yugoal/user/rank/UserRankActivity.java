package io.yugoal.user.rank;
/*

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.Constants;
import io.caoyu.wantodo.api.bean.CoinBean;
import io.caoyu.wantodo.databinding.ActivityUserRankBinding;

public class UserRankActivity extends BaseDataBindActivity<ActivityUserRankBinding> {

    private RankViewModel rankViewModel;
    public static void show(Context context) {
        Intent intent = new Intent(context, UserRankActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initViewModel() {
        rankViewModel = getViewModel(RankViewModel.class);
        rankViewModel.getCoinCount();
        rankViewModel.getCoinBeanMutableLiveData().observe(this, new androidx.lifecycle.Observer<CoinBean>() {
            @Override
            public void onChanged(CoinBean coinBean) {
                        dataBind.tvCoinCount.setText(String.valueOf(coinBean.getCoinCount()));
                        dataBind.tvRank.setText(String.valueOf(coinBean.getRank()));
                        dataBind.tvName.setText(SPUtils.getString(Constants.NAME,coinBean.getUsername()));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("个人积分");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_rank;
    }
}*/
