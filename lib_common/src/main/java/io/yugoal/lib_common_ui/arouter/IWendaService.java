package io.yugoal.lib_common_ui.arouter;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * user caoyu
 * date 2020/11/27
 * time 16:20
 */
public interface IWendaService extends IProvider {
    String WENDA_ROUTER = "/wenda/service/";
    String WENDA_SERVICE = WENDA_ROUTER + "wenda_service";

    Fragment getWendaFragment();
}
