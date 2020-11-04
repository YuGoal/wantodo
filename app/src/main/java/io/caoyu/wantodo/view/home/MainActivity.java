package io.caoyu.wantodo.view.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.ActivityMainBinding;
import io.caoyu.wantodo.view.all.AllFragment;
import io.caoyu.wantodo.view.home.adapter.CardViewAdapter;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;
import io.yugoal.lib_common_ui.utils.StatusBarUtil;
import io.yugoal.lib_utils.utils.StatusBarUtils;
import io.yugoal.lib_utils.utils.ToastUtils;


public class MainActivity extends BaseDataBindActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarColor(this,getResources().getColor(R.color.gray));
        setStatusBarLightMode(true);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initView() {
        initToolbar();
        initViewPager();
    }

    private void initToolbar() {
        dataBind.imgBtnNav.setOnClickListener(v -> {
            ToastUtils.showToast("功能开发中...");
        });
        dataBind.imgBtnSearch.setOnClickListener(v ->{
            ToastUtils.showToast("功能开发中...");
        });
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(AllFragment.newInstance());
        fragments.add(AllFragment.newInstance());
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
