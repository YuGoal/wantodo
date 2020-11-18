/*
package io.caoyu.wantodo.ui.all;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.databinding.FragmentAllBinding;
import io.caoyu.wantodo.ui.WebViewActivity;
import io.caoyu.wantodo.ui.all.adapter.AllAdapter;

*/
/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 *//*

public class AllFragment extends BaseDataBindFragment<FragmentAllBinding> implements SwipeRefreshLayout.OnRefreshListener {


    private AllAdapter allAdapter;
    private AllViewModel allViewModel;
    private List<ArticleBean.DatasBean> stepList;
    private RecyclerView recyclerView;
    private int page;

    public static AllFragment newInstance() {
        return new AllFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        recyclerView = getSuccessView();
        dataBind.statelayout.bindSuccessView(recyclerView);
        dataBind.statelayout.showLoadingView();
        dataBind.swiperefreshlayout.setOnRefreshListener(this);
    }

    @Override
    public void initViewModel() {
        allViewModel = getViewModel(AllViewModel.class);


    }

    @Override
    public void initData() {
        stepList = new ArrayList<>();

        allAdapter = new AllAdapter(getContext(),stepList);
        recyclerView.setAdapter(allAdapter);
        allAdapter.setOnItemClickListener(new AllAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, List<ArticleBean.DatasBean> myLiveList) {
                ArticleBean.DatasBean datasBean = myLiveList.get(pos);
                WebViewActivity.show(getContext(),datasBean.getLink(),datasBean.getTitle(),datasBean.getChapterName());
            }
        });

        allViewModel.getAllData(page);
        allViewModel.getArticleBeanMutableLiveData().observe(this, new Observer<ArticleBean>() {
            @Override
            public void onChanged(ArticleBean articleBean) {
                dataBind.swiperefreshlayout.setRefreshing(false);
                if (null != articleBean){
                    if (articleBean.getDatas().size()>0){
                        dataBind.statelayout.showSuccessView();
                        if (page == 0){
                            stepList.clear();
                            stepList.addAll(articleBean.getDatas());
                        }
                    }else {
                        if (page == 0){
                            dataBind.statelayout.showEmptyView();
                        }
                    }
                }
                stepList.addAll(articleBean.getDatas());
                allAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 0;
        allViewModel.getAllData(page);
    }
}
*/
