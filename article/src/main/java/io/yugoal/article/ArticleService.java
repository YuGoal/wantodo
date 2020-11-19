package io.yugoal.article;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yugoal.lib_common_ui.arouter.article.IArticleService;

/**
 * user caoyu
 * date 2020/11/19
 * time 11:22
 */
@Route(path = IArticleService.ARTICLE_SERVICE)
public class ArticleService implements IArticleService {
    @Override
    public Fragment getArticleFragment() {
        return new ArticleFragment();
    }

    @Override
    public void init(Context context) {

    }
}
