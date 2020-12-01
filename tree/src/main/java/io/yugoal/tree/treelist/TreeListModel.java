package io.yugoal.tree.treelist;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.model.MvvmBaseModel;
import io.yugoal.lib_base.base.model.PagingResult;
import io.yugoal.lib_common_ui.views.article.ArticleItemModel;
import io.yugoal.lib_network.WanTodoApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.observer.BaseObserver;
import io.yugoal.tree.api.TreeApiInterface;
import io.yugoal.tree.beans.Tree2Bean;
import retrofit2.Response;

/**
 * user caoyu
 * date 2020/12/1
 * time 15:26
 */
public class TreeListModel extends MvvmBaseModel<ArrayList<BaseCustomViewModel>> {
    private int cid;
    public TreeListModel() {
        super(true, "pref_key_treelist", null);
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
    protected void load() {
        pageNumber = isRefresh ? 0 : pageNumber + 1;
        WanTodoApi.getService(TreeApiInterface.class)
                .tree2(pageNumber,getCid())
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<Tree2Bean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Tree2Bean>> baseResponseResponse) {
                        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
                        for (Tree2Bean.DatasDTO datasBean : baseResponseResponse.body().data.getDatas()) {
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

    @Override
    public void refresh() {
        isRefresh = true;
        load();
    }

    public void loadNexPage() {
        isRefresh = false;
        load();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
