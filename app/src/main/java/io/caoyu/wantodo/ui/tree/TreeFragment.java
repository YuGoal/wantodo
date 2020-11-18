package io.caoyu.wantodo.ui.tree;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.bean.TreeBean;
import io.caoyu.wantodo.databinding.FragmentTreeBinding;
import io.caoyu.wantodo.ui.tree.adapter.TreeAdapter;

/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 */
public class TreeFragment extends BaseDataBindFragment<FragmentTreeBinding>{


    private TreeAdapter treeAdapter;
    private TreeViewModel treeViewModel;
    private List<TreeBean> stepList;
    private RecyclerView recyclerView;

    public static TreeFragment newInstance() {
        return new TreeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        recyclerView = getSuccessView();
        dataBind.statelayout.bindSuccessView(recyclerView);
        dataBind.statelayout.showLoadingView();
    }


    @Override
    public void initViewModel() {
        treeViewModel = getViewModel(TreeViewModel.class);
    }

    @Override
    public void initData() {
        stepList = new ArrayList<>();

        treeAdapter = new TreeAdapter(getContext(), stepList);
        recyclerView.setAdapter(treeAdapter);


        treeViewModel.getTree();
        treeViewModel.getTreeBeanMutableLiveData().observe(this, treeBean -> {
            if (null != treeBean) {
                if (treeBean.size() > 0) {
                    stepList.clear();
                    dataBind.statelayout.showSuccessView();
                    stepList.addAll(treeBean);
                    treeAdapter.notifyDataSetChanged();
                } else {
                    dataBind.statelayout.showEmptyView();
                }
            }
        });
    }
}
