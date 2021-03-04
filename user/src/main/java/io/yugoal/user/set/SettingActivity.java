package io.yugoal.user.set;
/*


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.ActivitySettingBinding;
import io.yugoal.lib_common_ui.CommonDialog;

*/
/**
 * 系统设置界面
 *//*

public class SettingActivity extends BaseDataBindActivity<ActivitySettingBinding> {

    public static void show(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initViewModel() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("系统设置");
        dataBind.tvLogout.setOnClickListener(v -> {
            CommonDialog commonDialog =  new CommonDialog(this,
                    "退出登录",
                    "确定退出吗？",
                    "确定",
                    "取消",
                    new CommonDialog.DialogClickListener() {
                        @Override
                        public void onDialogClick() {
                            finish();
                        }
                    });
            commonDialog.show();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}*/
