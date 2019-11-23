package io.caoyu.wantodo.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.caoyu.wantodo.R;
import io.caoyu.wantodo.model.CHANNEL;
import io.caoyu.wantodo.utils.ToastUtils;
import io.yugoal.lib_common_ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.TODO, CHANNEL.COMPLETED};

    @BindView(R.id.fab_add_todo)
    FloatingActionButton fabAddTodo;

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.more);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
        fabAddTodo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AddTodoActivity.class));
    }
}
