package io.yugoal.tree.treelist;

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

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.fragment.MvvmFragment;
import io.yugoal.tree.R;
import io.yugoal.tree.databinding.FragmentTreeListBinding;

/**
 * user caoyu
 * date 2020/12/1
 * time 15:48
 */
public class TreeListFragment extends MvvmFragment<FragmentTreeListBinding,TreeListViewModel, BaseCustomViewModel> {
    private static final String TAG = "TreeListFragment";
    private TreeRecyclerViewAdapter treeRecyclerViewAdapter;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (null!= bundle){
            int cid = bundle.getInt("cid");
            if (null!= viewModel){
                viewModel.init(cid);
            }
        }
        viewDataBinding.recyclerView.setHasFixedSize(true);
        viewDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        treeRecyclerViewAdapter = new TreeRecyclerViewAdapter();
        viewDataBinding.recyclerView.setAdapter(treeRecyclerViewAdapter);
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
    public TreeListViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(getFragmentTag(), TreeListViewModel.class);

            return viewModel;
        }
        return viewModel;
    }

    @Override
    public void onListItemInserted(ObservableList<BaseCustomViewModel> sender) {
        treeRecyclerViewAdapter.setData(sender);
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
