package io.yugoal.tree.tree1;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.fragment.MvvmFragment;
import io.yugoal.tree.R;
import io.yugoal.tree.databinding.FragmentTreeBinding;


/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 */
public class TreeFragment extends MvvmFragment<FragmentTreeBinding, TreeViewModel, BaseCustomViewModel> {
    private static final String TAG = "TreeFragment";
    private TreeAdapter treeAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    public TreeViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(getFragmentTag(), TreeViewModel.class);
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
        treeAdapter = new TreeAdapter();
        viewDataBinding.recyclerView.setAdapter(treeAdapter);
        viewDataBinding.smartRefreshLayout.setRefreshHeader(new WaterDropHeader(getContext()));
        viewDataBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.tryRefresh();
            }
        });
        setLoadSir(viewDataBinding.smartRefreshLayout);
        showLoading();
    }

    @Override
    public void onListItemInserted(ObservableList sender) {
        treeAdapter.setData(sender);
        viewDataBinding.smartRefreshLayout.finishLoadMore();
        viewDataBinding.smartRefreshLayout.finishRefresh();
        showSuccess();
    }

    @Override
    public int getBindingVariable() {
        return 0;
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
