package io.yugoal.wenda;

import android.text.Html;
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
import io.yugoal.lib_utils.utils.StringUtils;
import io.yugoal.wenda.api.WendaApiInterface;
import io.yugoal.wenda.beans.WendaBean;
import io.yugoal.wenda.ui.WendaItemModel;
import retrofit2.Response;

/**
 * user caoyu
 * date 2020/11/27
 * time 16:40
 */
public class WendaModel extends MvvmBaseModel<ArrayList<BaseCustomViewModel>> {

    public WendaModel() {
        super(true, "pref_key_wenda", null);
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
        WanTodoApi.getService(WendaApiInterface.class)
                .wenda(pageNumber)
                .compose(WanTodoApi.getInstance().applySchedulers(new BaseObserver<Response<BaseResponse<WendaBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<WendaBean>> baseResponseResponse) {
                        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
                        for (WendaBean.DatasDTO datasBean : baseResponseResponse.body().data.getDatas()) {
                            if (datasBean != null) {
                                WendaItemModel wendaItemModel = new WendaItemModel();
                                if (TextUtils.isEmpty(datasBean.getAuthor())) {
                                    wendaItemModel.author = datasBean.getShareUser();
                                } else {
                                    wendaItemModel.author = datasBean.getAuthor();
                                }
                                wendaItemModel.link = datasBean.getLink();
                                wendaItemModel.title = datasBean.getTitle();
                                wendaItemModel.tags = datasBean.getSuperChapterName();
                                wendaItemModel.niceShareDate = datasBean.getNiceShareDate();
                                String desc = Html.fromHtml(datasBean.getDesc()).toString();
                                desc = StringUtils.removeAllBank(desc, 2);
                                wendaItemModel.desc = desc;
                                baseViewModels.add(wendaItemModel);
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
}
