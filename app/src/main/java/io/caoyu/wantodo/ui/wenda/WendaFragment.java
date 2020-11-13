package io.caoyu.wantodo.ui.wenda;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.bean.WendaBean;
import io.caoyu.wantodo.databinding.FragmentAllBinding;
import io.caoyu.wantodo.ui.WebViewActivity;
import io.yugoal.lib_base.base.fragment.BaseDataBindFragment;

/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 */
public class WendaFragment extends BaseDataBindFragment<FragmentAllBinding> implements SwipeRefreshLayout.OnRefreshListener {


    private WendaAdapter wendaAdapter;
    private WendaViewModel wendaViewModel;
    private List<WendaBean.DatasDTO> stepList;
    private RecyclerView recyclerView;
    private int page;

    public static WendaFragment newInstance() {
        return new WendaFragment();
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
        wendaViewModel = getViewModel(WendaViewModel.class);


    }

    @Override
    public void initData() {
        stepList = new ArrayList<>();

        wendaAdapter = new WendaAdapter(getContext(), stepList);
        recyclerView.setAdapter(wendaAdapter);
        wendaAdapter.setOnItemClickListener(new WendaAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, List<WendaBean.DatasDTO> myLiveList) {
                WendaBean.DatasDTO datasBean = myLiveList.get(pos);
                WebViewActivity.show(getContext(), datasBean.getLink(), datasBean.getTitle(), datasBean.getChapterName());
            }
        });

        wendaViewModel.getWendaData(page);
        wendaViewModel.getWendaBeanMutableLiveData().observe(this, new Observer<WendaBean>() {
            @Override
            public void onChanged(WendaBean wendaBean) {
                dataBind.swiperefreshlayout.setRefreshing(false);
                if (null != wendaBean) {
                    if (wendaBean.getDatas().size() > 0) {
                        dataBind.statelayout.showSuccessView();
                        if (page == 0) {
                            stepList.clear();
                            stepList.addAll(wendaBean.getDatas());
                        }
                    } else {
                        page--;
                        if (page == 0) {
                            dataBind.statelayout.showEmptyView();
                        }
                    }
                }
                stepList.addAll(wendaBean.getDatas());
                wendaAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 0;
        wendaViewModel.getWendaData(page);
    }
}
