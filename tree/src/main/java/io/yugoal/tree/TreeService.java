package io.yugoal.tree;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yugoal.lib_common_ui.arouter.ITreeService;
import io.yugoal.tree.tree1.TreeFragment;

/**
 * user caoyu
 * date 2020/11/19
 * time 11:22
 */
@Route(path = ITreeService.TREE_SERVICE)
public class TreeService implements ITreeService {
    @Override
    public void init(Context context) {

    }

    @Override
    public Fragment getTreeFragment() {
        return new TreeFragment();
    }
}
