package io.yugoal.article;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import io.yugoal.article.beans.ArticleBean;
import io.yugoal.article.databinding.FragmentArticleBinding;
import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.fragment.MvvmFragment;


/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 首页文章
 */
public class ArticleFragment extends MvvmFragment<FragmentArticleBinding, ArticleViewModel, BaseCustomViewModel> {


    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public ArticleViewModel getViewModel() {
        return null;
    }

    @Override
    public void onListItemInserted(ObservableList<BaseCustomViewModel> sender) {

    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return null;
    }
}