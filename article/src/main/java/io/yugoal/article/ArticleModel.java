package io.yugoal.article;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.yugoal.article.api.ArticleApiInterface;
import io.yugoal.article.beans.ArticleBean;
import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.model.MvvmBaseModel;
import io.yugoal.lib_base.base.model.PagingResult;
import io.yugoal.lib_common_ui.views.article.ArticleItemModel;
import io.yugoal.lib_network.WanTodoApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.observer.BaseObserver;
import retrofit2.Response;

/**
 * user caoyu
 * date 2020/11/18
 * time 16:57
 * 文章model
 */
public class ArticleModel extends MvvmBaseModel<ArrayList<BaseCustomViewModel>> {

    public ArticleModel() {
        super(true, "pref_key_article", null);
    }

    @Override
    protected boolean isNeedToUpdate() {
        return false;
    }

    @Override
    public Type getTClass() {
        return new TypeToken<ArrayList<ArticleItemModel>>() {
        }.getType();
    }

    @Override
    public void refresh() {
        isRefresh = true;
        load();
    }

    public void loadNexPage() {
        isRefresh = false;
        load();
    }

    @Override
    protected void load() {
        pageNumber = isRefresh ? 0 : pageNumber + 1;
        WanTodoApi.getService(ArticleApiInterface.class)
                .article(pageNumber)
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<ArticleBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ArticleBean>> baseResponseResponse) {
                        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
                        for (ArticleBean.DatasBean datasBean : baseResponseResponse.body().data.getDatas()) {
                            if (datasBean != null) {
                                ArticleItemModel viewModel = new ArticleItemModel();
                                if (TextUtils.isEmpty(datasBean.getAuthor())) {
                                    viewModel.author = datasBean.getShareUser();
                                } else {
                                    viewModel.author = datasBean.getAuthor();
                                }
                                viewModel.link = datasBean.getLink();
                                viewModel.title = datasBean.getTitle();
                                viewModel.tags = datasBean.getSuperChapterName();
                                viewModel.niceShareDate = datasBean.getNiceShareDate();
                                baseViewModels.add(viewModel);
                            }
                        }
                        loadSuccess(baseViewModels, new PagingResult(baseViewModels.size() == 0, isRefresh, baseViewModels.size() == 0));
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        loadFail(e.getMessage(), new PagingResult(true, isRefresh, false));

                    }
                }));
    }
}
