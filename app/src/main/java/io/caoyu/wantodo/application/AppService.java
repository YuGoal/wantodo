package io.caoyu.wantodo.application;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yugoal.lib_common_ui.arouter.IAppService;

/**
 * user caoyu
 * date 2020/11/26
 * time 17:17
 */
@Route(path = IAppService.APP_SERVICE)
public class AppService implements IAppService {
    @Override
    public Context getApp() {
        return TodoApplication.getContext();
    }

    @Override
    public void init(Context context) {

    }
}
