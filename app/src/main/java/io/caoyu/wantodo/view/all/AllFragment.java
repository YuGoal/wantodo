package io.caoyu.wantodo.view.all;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.databinding.FragmentAllBinding;
import io.caoyu.wantodo.view.all.adapter.AllAdapter;
import io.yugoal.lib_base.base.fragment.BaseDataBindFragment;

/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 */
public class AllFragment extends BaseDataBindFragment<FragmentAllBinding> implements SwipeRefreshLayout.OnRefreshListener {


    private AllAdapter allAdapter;
    private AllViewModel allViewModel;
    private List<ArticleBean.DatasBean> stepList;
    private RecyclerView recyclerView;

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

        allViewModel.getAllData();
        allViewModel.getArticleBeanMutableLiveData().observe(this, new Observer<ArticleBean>() {
            @Override
            public void onChanged(ArticleBean articleBean) {
                dataBind.swiperefreshlayout.setRefreshing(false);
                dataBind.statelayout.showSuccessView();
                stepList.clear();
                stepList.addAll(articleBean.getDatas());
                allAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        allViewModel.getAllData();
    }
}
