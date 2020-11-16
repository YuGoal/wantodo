package io.yugoal.lib_base.loadsir.callback;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;

import io.yugoal.lib_base.R;

/**
 * user caoyu
 * date 2020/11/16
 * time 17:41
 * 错误界面回调
 */
public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.network404_layout;
    }
}
