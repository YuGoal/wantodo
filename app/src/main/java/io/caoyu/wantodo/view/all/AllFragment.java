package io.caoyu.wantodo.view.all;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.FragmentAllBinding;
import io.caoyu.wantodo.view.all.adapter.AllAdapter;
import io.yugoal.lib_base.base.fragment.BaseDataBindFragment;

/**
 * user caoyu
 * date 2020/11/4
 * time 14:33
 * 全部
 */
public class AllFragment extends BaseDataBindFragment<FragmentAllBinding> {


    private AllAdapter allAdapter;
    private AllViewModel allViewModel;

    public static AllFragment newInstance(){
        return new AllFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initViewModel() {
       allViewModel =  getViewModel(AllViewModel.class);
       allAdapter = new AllAdapter(getContext(),allViewModel.getAllData());
       dataBind.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       dataBind.recyclerView.setAdapter(allAdapter);
    }

    @Override
    public void initData() {

    }
}
