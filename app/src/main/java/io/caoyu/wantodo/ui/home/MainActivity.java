package io.caoyu.wantodo.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.TextureView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.Constants;
import io.caoyu.wantodo.databinding.ActivityMainBinding;
import io.caoyu.wantodo.ui.all.AllFragment;
import io.caoyu.wantodo.ui.home.adapter.CardViewAdapter;
import io.caoyu.wantodo.ui.mine.loginreg.LoginActivity;
import io.caoyu.wantodo.ui.mine.rank.UserRankActivity;
import io.caoyu.wantodo.ui.mine.set.SettingActivity;
import io.caoyu.wantodo.ui.wenda.WendaFragment;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;
import io.yugoal.lib_base.SPUtils;
import io.yugoal.lib_utils.utils.ToastUtils;


public class MainActivity extends BaseDataBindActivity<ActivityMainBinding> {

    @Override
    public void initViewModel() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = SPUtils.getString(Constants.NAME,"");
        if (!TextUtils.isEmpty(name)){
            dataBind.drawerView.tvName.setText(name);
            dataBind.drawerView.charAvatarView.setText(name);
        }
    }

    private void initView() {
        initToolbar();
        initViewPager();
        initEvent();
    }

    private void initEvent() {
        dataBind.drawerView.tvSetting.setOnClickListener(v -> {
            SettingActivity.show(this);
        });
    }

    @SuppressLint("RtlHardcoded")
    private void initToolbar() {
        dataBind.imgBtnNav.setOnClickListener(v -> {
            dataBind.drawer.openDrawer(Gravity.LEFT);
        });
        dataBind.imgBtnSearch.setOnClickListener(v ->{
            ToastUtils.showToast("功能开发中...");
        });
        dataBind.drawerView.charAvatarView.setOnClickListener(v->{
            if (TextUtils.isEmpty(SPUtils.getString(Constants.NAME,""))){
                LoginActivity.show(this);
            }else {
                // TODO: 2020/11/13 个人积分
                UserRankActivity.show(this);
            }
        });
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(AllFragment.newInstance());
        fragments.add(WendaFragment.newInstance());
        fragments.add(AllFragment.newInstance());
        CardViewAdapter cardViewAdapter = new CardViewAdapter(getSupportFragmentManager(), fragments);
        dataBind.viewpager.setAdapter(cardViewAdapter);
        dataBind.tabs.setupWithViewPager(dataBind.viewpager);
        dataBind.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
