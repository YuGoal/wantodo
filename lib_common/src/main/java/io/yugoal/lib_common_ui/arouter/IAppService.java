package io.yugoal.lib_common_ui.arouter;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * user caoyu
 * date 2020/11/26
 * time 17:08
 */
public interface IAppService extends IProvider {

    String APP_ROUTER = "/application/";
    String APP_SERVICE = APP_ROUTER + "app_service";

    Context getApp();
}
