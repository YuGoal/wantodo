package io.yugoal.lib_base.loadsir.callback;

import com.kingja.loadsir.callback.Callback;

import io.yugoal.lib_base.R;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

}
