package io.yugoal.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.yugoal.article.databinding.ActivityMineArticleBinding;
import io.yugoal.article.favorites.FavoritesFragment;
import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_base.base.fragment.MvvmFragment;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;


/**
 * user caoyu
 * date 2021/3/8
 * time 16:40
 * 我的收藏 我的分享 等一系列
 */
public class MineArticleActivity extends MvvmActivity<ActivityMineArticleBinding, MvvmBaseViewModel> {

    private MvvmFragment mvvmFragment;

    public static void show(Context context, int type) {
        Intent intent = new Intent(context, MineArticleActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_article;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            return;
        }
        if (getIntent() != null) {
            //数据类型
            int type = getIntent().getIntExtra("type", 0);
            switch (type) {
                case 0://收藏
                    mvvmFragment = new FavoritesFragment();
                    initToolbar("我的收藏");
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mvvmFragment).commit();
        }
    }
}
