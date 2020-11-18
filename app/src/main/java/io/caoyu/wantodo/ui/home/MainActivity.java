package io.caoyu.wantodo.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.Constants;
import io.caoyu.wantodo.databinding.ActivityMainBinding;
import io.caoyu.wantodo.ui.home.adapter.CardViewAdapter;
import io.caoyu.wantodo.ui.mine.loginreg.LoginActivity;
import io.caoyu.wantodo.ui.mine.rank.UserRankActivity;
import io.caoyu.wantodo.ui.mine.set.SettingActivity;
import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_base.base.preference.PreferencesUtil;
import io.yugoal.lib_base.base.utils.ToastUtil;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;
import io.yugoal.lib_common_ui.arouter.RouteServiceManager;
import io.yugoal.lib_common_ui.arouter.article.IArticleService;


public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel> {

    private IArticleService iArticleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initViewPager();
        initEvent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    public void onResume() {
        super.onResume();
        String name = PreferencesUtil.getInstance().getString(Constants.NAME,"");
        if (!TextUtils.isEmpty(name)){
            viewDataBinding.drawerView.tvName.setText(name);
            viewDataBinding.drawerView.charAvatarView.setText(name);
        }
    }

    private void initEvent() {
        viewDataBinding.drawerView.tvSetting.setOnClickListener(v -> {
            SettingActivity.show(this);
        });
    }

    @SuppressLint("RtlHardcoded")
    private void initToolbar() {
        viewDataBinding.imgBtnNav.setOnClickListener(v -> {
            viewDataBinding.drawer.openDrawer(Gravity.LEFT);
        });
        viewDataBinding.imgBtnSearch.setOnClickListener(v ->{
            ToastUtil.show("功能开发中...");
        });
        viewDataBinding.drawerView.charAvatarView.setOnClickListener(v->{
            if (TextUtils.isEmpty(PreferencesUtil.getInstance().getString(Constants.NAME,""))){
                LoginActivity.show(this);
            }else {
                // TODO: 2020/11/13 个人积分
                UserRankActivity.show(this);
            }
        });
    }

    private void initViewPager() {
        iArticleService = RouteServiceManager.provide(IArticleService.class,IArticleService.ARTICLE_ROUTER);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(iArticleService.getArticleFragment());
        fragments.add(iArticleService.getArticleFragment());
        fragments.add(iArticleService.getArticleFragment());
        CardViewAdapter cardViewAdapter = new CardViewAdapter(getSupportFragmentManager(), fragments);
        viewDataBinding.viewpager.setAdapter(cardViewAdapter);
        viewDataBinding.tabs.setupWithViewPager(viewDataBinding.viewpager);
        viewDataBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
