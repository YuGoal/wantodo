package io.yugoal.article.service;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.yugoal.article.ArticleFragment;
import io.yugoal.article.MineArticleActivity;
import io.yugoal.lib_common_ui.arouter.IArticleService;

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
    public void showMine(Context context,int type) {
        MineArticleActivity.show(context,type);
    }

    @Override
    public void init(Context context) {

    }
}
