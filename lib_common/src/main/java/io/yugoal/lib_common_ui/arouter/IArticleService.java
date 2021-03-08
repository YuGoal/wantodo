package io.yugoal.lib_common_ui.arouter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * user caoyu
 * date 2020/11/18
 * time 17:27
 */
public interface IArticleService extends IProvider {
    String ARTICLE_ROUTER = "/article/service/";
    String ARTICLE_SERVICE = ARTICLE_ROUTER + "article_service";

    Fragment getArticleFragment();

    void showMine(Context context, int type);
}
