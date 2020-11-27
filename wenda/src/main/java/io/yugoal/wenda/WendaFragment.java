package io.yugoal.wenda;

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

import io.yugoal.lib_base.base.fragment.MvvmFragment;
import io.yugoal.wenda.ui.WendaItemModel;
import yugoal.wenda.R;
import yugoal.wenda.databinding.FragmentAllBinding;


/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 */
public class WendaFragment extends MvvmFragment<FragmentAllBinding, WendaViewModel, WendaItemModel> {
    private static final String TAG = "WendaFragment";
    private WendaAdapter wendaAdapter;

    public static WendaFragment newInstance() {
        return new WendaFragment();
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public WendaViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(getFragmentTag(),WendaViewModel.class);
            viewModel.init();
            return viewModel;
        }
        return viewModel;
    }

    @Override
    public void onListItemInserted(ObservableList<WendaItemModel> sender) {
        wendaAdapter.setData(sender);
        viewDataBinding.smartRefreshLayout.finishLoadMore();
        viewDataBinding.smartRefreshLayout.finishRefresh();
        showSuccess();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.recyclerView.setHasFixedSize(true);
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wendaAdapter = new WendaAdapter();
        viewDataBinding.recyclerView.setAdapter(wendaAdapter);
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
    protected void onRetryBtnClick() {
        viewModel.tryRefresh();
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

}
