package io.yugoal.article.favorites;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.yugoal.article.R;
import io.yugoal.article.adapter.ArticleRecyclerViewAdapter;
import io.yugoal.article.databinding.FragmentMineBinding;
import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.fragment.MvvmFragment;


/**
 * user caoyu
 * date 2021/3/8
 * time 16:47
 */
public class FavoritesFragment extends MvvmFragment<FragmentMineBinding, FavoritesViewModel, BaseCustomViewModel> {
    private static final String TAG = "ArticleFragment";
    private ArticleRecyclerViewAdapter mAdapter;
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public FavoritesViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(getFragmentTag(),FavoritesViewModel.class);
            viewModel.init();
            return viewModel;
        }
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.recyclerView.setHasFixedSize(true);
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ArticleRecyclerViewAdapter();
        viewDataBinding.recyclerView.setAdapter(mAdapter);
        viewDataBinding.smartRefreshLayout.setRefreshHeader(new WaterDropHeader(getContext()));
        viewDataBinding.smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        viewDataBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.tryRefresh();
            }
        });
        viewDataBinding.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.tryToLoadNextPage();
            }
        });
        setLoadSir(viewDataBinding.smartRefreshLayout);
        showLoading();
    }

    @Override
    public void onListItemInserted(ObservableList<BaseCustomViewModel> sender) {
        mAdapter.setData(sender);
        viewDataBinding.smartRefreshLayout.finishLoadMore();
        viewDataBinding.smartRefreshLayout.finishRefresh();
        showSuccess();
    }

    @Override
    protected void onRetryBtnClick() {
        viewModel.tryRefresh();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }
}
