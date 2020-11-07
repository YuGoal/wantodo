package io.caoyu.wantodo.view.mine;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.ActivitySettingBinding;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;

/**
 * 系统设置界面
 */
public class SettingActivity extends BaseDataBindActivity<ActivitySettingBinding> {

    public static void show(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind.toolbar.setTitle("系统设置");
        dataBind.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        dataBind.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}