package io.yugoal.lib_common_ui.arouter;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * user caoyu
 * date 2020/11/24
 * time 17:57
 */
public interface ITreeService extends IProvider {
    String TREE_ROUTER = "/tree/";
    String TREE_SERVICE = TREE_ROUTER + "tree_service";

    Fragment getTreeFragment();
}
