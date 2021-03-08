package io.caoyu.wantodo.ui.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.application.TodoApplication;
import io.caoyu.wantodo.databinding.ActivityMainBinding;
import io.caoyu.wantodo.ui.home.adapter.CardViewAdapter;
import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_base.base.preference.SPUtils;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;
import io.yugoal.lib_common_ui.arouter.IArticleService;
import io.yugoal.lib_common_ui.arouter.ITreeService;
import io.yugoal.lib_common_ui.arouter.IUserService;
import io.yugoal.lib_common_ui.arouter.IWendaService;
import io.yugoal.lib_common_ui.arouter.RouteServiceManager;
import io.yugoal.lib_common_ui.utils.GuideSPUtils;
import io.yugoal.lib_utils.utils.ToastUtil;
import io.yugoal.user.api.Constants;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;


public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel> {

    private IArticleService iArticleService;
    private ITreeService iTreeService;
    private IWendaService iWendaService;
    private IUserService iUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initViewPager();
        initEvent();
        showIfFirst();
    }

    private void showIfFirst() {
        if (!GuideSPUtils.getInstance().isPrivacyPolicyShown()) {
            Dialog dialog = new Dialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_privacy_policy, null);
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            view.findViewById(R.id.dialog_privacy_policy_tv_yes).setOnClickListener(v -> {
                GuideSPUtils.getInstance().setPrivacyPolicyShown();
                dialog.dismiss();
            });
            view.findViewById(R.id.dialog_privacy_policy_tv_no).setOnClickListener(v -> {
                TodoApplication.exitApp();
                dialog.dismiss();
            });
        }
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
        if (null == iUserService) {
            return;
        }
        if (!TextUtils.isEmpty(iUserService.getNickname())) {
            viewDataBinding.drawerView.tvName.setText(iUserService.getNickname());
            viewDataBinding.drawerView.charAvatarView.setText(iUserService.getNickname());
        } else {
            viewDataBinding.drawerView.tvName.setText("请先登录");
            viewDataBinding.drawerView.charAvatarView.setText("W");
        }
    }

    private void initEvent() {
        viewDataBinding.drawerView.tvSetting.setOnClickListener(v -> {
            iUserService.showSet();
        });
        viewDataBinding.drawerView.switchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        viewDataBinding.drawerView.tvFavorites.setOnClickListener(v -> {
            if (iUserService.isLogin(MainActivity.this)) {
                iArticleService.showMine(MainActivity.this, 0);
            }
        });
    }

    @SuppressLint("RtlHardcoded")
    private void initToolbar() {
        viewDataBinding.imgBtnNav.setOnClickListener(v -> {
            viewDataBinding.drawer.openDrawer(Gravity.LEFT);
        });
        viewDataBinding.imgBtnSearch.setOnClickListener(v -> {
            ToastUtil.show("功能开发中...");
        });
        viewDataBinding.drawerView.lineUser.setOnClickListener(v -> {
            if (TextUtils.isEmpty(SPUtils.getInstance().get(Constants.NAME, ""))) {
                iUserService.showLogin();
            } else {
                iUserService.showRank();
            }
        });
    }

    private void initViewPager() {
        iArticleService = RouteServiceManager.provide(IArticleService.class, IArticleService.ARTICLE_SERVICE);
        iTreeService = RouteServiceManager.provide(ITreeService.class, ITreeService.TREE_SERVICE);
        iWendaService = RouteServiceManager.provide(IWendaService.class, IWendaService.WENDA_SERVICE);
        iUserService = RouteServiceManager.provide(IUserService.class, IUserService.USER_SERVICE);
        iUserService.init(this);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(iArticleService.getArticleFragment());
        fragments.add(iWendaService.getWendaFragment());
        fragments.add(iTreeService.getTreeFragment());
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
