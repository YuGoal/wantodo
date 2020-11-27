package io.yugoal.wenda.service;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yugoal.lib_common_ui.arouter.IWendaService;
import io.yugoal.wenda.WendaFragment;

/**
 * user caoyu
 * date 2020/11/27
 * time 16:23
 */
@Route(path = IWendaService.WENDA_SERVICE)
public class WendaService implements IWendaService {
    @Override
    public Fragment getWendaFragment() {
        return WendaFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
