package io.caoyu.wantodo.view.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.ActivityMainBinding;
import io.caoyu.wantodo.model.CHANNEL;
import io.caoyu.wantodo.utils.ToastUtils;
import io.yugoal.lib_common_ui.base.BaseDataBindActivity;

public class MainActivity extends BaseDataBindActivity<ActivityMainBinding>{

    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.TODO, CHANNEL.COMPLETED};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initView() {
        dataBind.toolbar.inflateMenu(R.menu.more);
        dataBind.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_more:
                        // TODO: 2019/11/13 更多
                        ToastUtils.showToast("功能开发中...");
                        break;
                }
                return true;
            }
        });
        dataBind.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019/11/13 登录注册/个人信息
                ToastUtils.showToast("功能开发中...");
            }
        });
        TodoFragment todoFragment = new TodoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,todoFragment);
        transaction.commit();
    }
}
