package io.caoyu.wantodo.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.caoyu.wantodo.R;
import io.caoyu.wantodo.constant.Constant;
import io.caoyu.wantodo.repository.UserController;
import io.caoyu.wantodo.utils.ToastUtils;
import io.yugoal.lib_common_ui.base.BaseActivity;
import io.yugoal.lib_common_ui.utils.SpUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.fab_add_todo)
    FloatingActionButton fabAddTodo;

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        //自动登录
        if (!SpUtils.getValue(Constant.IS_LOGIN, false)) {
            UserController.getInstance().login();
        }
    }

    private void initView() {
        //初始化主界面
        initHome();
        //初始化抽屉
        initNav();
    }

    private void initHome() {
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

        TodoFragment todoFragment = new TodoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, todoFragment);
        transaction.commit();
        fabAddTodo.setOnClickListener(this);
    }

    /**
     * 初始化抽屉
     */
    private void initNav() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);

        View headview = navView.getHeaderView(0);
        TextView t = headview.findViewById(R.id.tv_nickname);
        t.setText(SpUtils.getValue(Constant.NICKNAME, getResources().getString(R.string.login_register)));
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SpUtils.getValue(Constant.IS_LOGIN, false)) {
                    showLoginReg();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, AddTodoActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // TODO: 2019/11/23 侧滑栏菜单
        return false;
    }

    /**
     * 登录/注册
     */
    private void showLoginReg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login_reg, null);

        builder.setView(view);
        builder.create().show();
    }

}
