package io.caoyu.wantodo.view.home;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.caoyu.wantodo.R;
import io.caoyu.wantodo.utils.KeyboardStateObserver;
import io.yugoal.lib_common_ui.base.BaseActivity;

/**
 * yugoal
 * 2020-01-21
 * 18:02
 */
public class QuickToDoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_quick_todo)
    EditText etQuickTodo;
    @BindView(R.id.line_quick)
    LinearLayout lineQuick;
    @BindView(R.id.relative_btn)
    RelativeLayout relativeBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Objects.requireNonNull(getWindow()).addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            Objects.requireNonNull(getWindow()).setStatusBarColor(getResources().getColor(R.color.white));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_quick_todo);
        ButterKnife.bind(this);
        toolbar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
        KeyboardStateObserver.getKeyboardStateObserver(this).
                setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
                    @Override
                    public void onKeyboardShow(int heightDifference) {
                        setEtQuickTodo((ScreenUtils.getScreenHeight() -
                                heightDifference - etQuickTodo.getHeight() ));
                    }

                    @Override
                    public void onKeyboardHide() {
                        LogUtils.d("onKeyboardHide","onKeyboardHide");
                        setEtQuickTodo((ScreenUtils.getScreenHeight() - lineQuick.getHeight()));
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        KeyboardUtils.showSoftInput(lineQuick);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            out();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setEtQuickTodo(int dpTop) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                lineQuick.getHeight());
        layoutParams.setMargins(0, dpTop, 0, 0);
        lineQuick.setLayoutParams(layoutParams);
    }

    /**
     * 退出快捷todo
     */
    private void out() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.hideSoftInput(lineQuick);
    }
}
