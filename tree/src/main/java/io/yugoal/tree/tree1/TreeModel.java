package io.yugoal.tree.tree1;

import androidx.databinding.ObservableArrayList;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.model.MvvmBaseModel;
import io.yugoal.lib_base.base.model.PagingResult;
import io.yugoal.lib_common_ui.views.article.ArticleItemModel;
import io.yugoal.tree.views.TreeItem2Model;
import io.yugoal.tree.views.TreeItemModel;
import io.yugoal.lib_network.WanTodoApi;
import io.yugoal.lib_network.beans.BaseResponse;
import io.yugoal.lib_network.observer.BaseObserver;
import io.yugoal.tree.api.TreeApiInterface;
import io.yugoal.tree.beans.TreeBean;
import retrofit2.Response;

/**
 * user caoyu
 * date 2020/11/25
 * time 10:19
 */
public class TreeModel extends MvvmBaseModel<ArrayList<BaseCustomViewModel>> {
    public TreeModel() {
        super(true, "pref_key_tree", null);
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

    @Override
    protected void load() {
        WanTodoApi.getService(TreeApiInterface.class)
                .tree()
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<List<TreeBean>>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<List<TreeBean>>> baseResponseResponse) {
                        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
                        for (TreeBean datasBean : baseResponseResponse.body().data) {
                            if (datasBean != null) {
                                TreeItemModel viewModel = new TreeItemModel();
                                viewModel.item2Model = new ObservableArrayList<>();
                                viewModel.name = datasBean.getName();
                                for (TreeBean.ChildrenBean childrenBean : datasBean.getChildren()){
                                    TreeItem2Model treeItem2Model = new TreeItem2Model();
                                    treeItem2Model.id = childrenBean.getId();
                                    treeItem2Model.name = childrenBean.getName();
                                    viewModel.item2Model.add(treeItem2Model);
                                }
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
