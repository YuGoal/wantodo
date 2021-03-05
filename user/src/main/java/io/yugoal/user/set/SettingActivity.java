package io.yugoal.user.set;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_base.base.preference.SPUtils;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;
import io.yugoal.lib_common_ui.CommonDialog;
import io.yugoal.lib_network.cookie.PersistentCookieStore;
import io.yugoal.lib_utils.utils.CacheUtils;
import io.yugoal.user.ISkill;
import io.yugoal.user.R;
import io.yugoal.user.api.Constants;
import io.yugoal.user.databinding.ActivitySettingBinding;

/**
 * 系统设置界面
 */

public class SettingActivity extends MvvmActivity<ActivitySettingBinding, MvvmBaseViewModel> {

    public static void show(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("系统设置");
        if (TextUtils.isEmpty(SPUtils.getInstance().get(Constants.NAME, ""))) {
            viewDataBinding.tvLogout.setVisibility(View.GONE);
        } else {
            viewDataBinding.tvLogout.setOnClickListener(v -> {
                CommonDialog commonDialog = new CommonDialog(this,
                        "退出登录",
                        "确定退出吗？",
                        "确定",
                        "取消",
                        new CommonDialog.DialogClickListener() {
                            @Override
                            public void onDialogClick() {
                                SPUtils.getInstance().save(Constants.NAME, "");
                                SPUtils.getInstance().save(Constants.ID, "");
                                PersistentCookieStore persistentCookieStore = new PersistentCookieStore();
                                persistentCookieStore.removeAll();
                                finish();
                            }
                        });
                commonDialog.show();
            });
        }

        viewDataBinding.relativeCache.setOnClickListener(v -> {
            CommonDialog commonDialog = new CommonDialog(this,
                    "清除缓存",
                    "确定要清除缓存吗？",
                    "确定",
                    "取消",
                    () -> clearCache());
            commonDialog.show();
        });
        viewDataBinding.relativeVersion.setOnClickListener(view -> {
            ISkill impl = (ISkill) ARouter.getInstance().build("/service/ISkillImpl").navigation();
            impl.checkVersion();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }

    public void clearCache() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                CacheUtils.clearAllCache();
                String size = CacheUtils.getTotalCacheSize();
                //WanCache.getInstance().openDiskLruCache();
                if (!emitter.isDisposed()) {
                    emitter.onNext(size);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String size) {
                getCacheSize();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void getCacheSize() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String size = CacheUtils.getTotalCacheSize();
                if (!emitter.isDisposed()) {
                    emitter.onNext(size);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String size) {
                //viewDataBinding..setText(size);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
