package io.caoyu.wantodo.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.caoyu.wantodo.BuglyUtils;
import io.yugoal.lib_utils.utils.ToastUtil;
import io.yugoal.user.ISkill;

/**
 * user caoyu
 * date 2021/3/5
 * time 16:06
 */
@Route(path = "/service/ISkillImpl")
public class ISkillImpl implements ISkill {

    @Override
    public void checkVersion() {
        BuglyUtils.checkAppUpdate(true);
    }

    @Override
    public void init(Context context) {

    }
}
