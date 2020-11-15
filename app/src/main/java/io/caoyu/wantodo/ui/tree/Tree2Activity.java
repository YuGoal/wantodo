package io.caoyu.wantodo.ui.tree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.api.bean.Tree2Bean;
import io.caoyu.wantodo.databinding.ActivityTree2Binding;
import io.caoyu.wantodo.ui.WebViewActivity;
import io.caoyu.wantodo.ui.all.AllViewModel;
import io.caoyu.wantodo.ui.all.adapter.AllAdapter;
import io.caoyu.wantodo.ui.tree.adapter.TreeArticleAdapter;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;

public class Tree2Activity extends BaseDataBindActivity<ActivityTree2Binding> {

    private TreeViewModel treeViewModel;

    private TreeArticleAdapter treeArticleAdapter;
    private List<Tree2Bean.DatasDTO> stepList;
    private RecyclerView recyclerView;
    private int page = 0;
    private int cid;

    public static void show(Context context, String title, int cid) {
        Intent intent = new Intent(context, Tree2Activity.class);
        intent.putExtra("title", title);
        intent.putExtra("cid", cid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (null != intent) {
            cid = intent.getIntExtra("cid", 0);
            String title = intent.getStringExtra("title");
            if (!TextUtils.isEmpty(title)) {
                initToolbar(title);
            }
        }

        recyclerView = getSuccessView();
        dataBind.statelayout.bindSuccessView(recyclerView);
        dataBind.statelayout.showLoadingView();
        initData();
    }


    @Override
    public void initViewModel() {
        treeViewModel = getViewModel(TreeViewModel.class);
    }

    public void initData() {
        stepList = new ArrayList<>();

        treeArticleAdapter = new TreeArticleAdapter(this, stepList);
        recyclerView.setAdapter(treeArticleAdapter);
        treeArticleAdapter.setOnItemClickListener(new TreeArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, List<Tree2Bean.DatasDTO> myLiveList) {
                Tree2Bean.DatasDTO datasBean = myLiveList.get(pos);
                WebViewActivity.show(Tree2Activity.this, datasBean.getLink(), datasBean.getTitle(), datasBean.getChapterName());
            }
        });

        treeViewModel.getTree2(page, cid);
        treeViewModel.getTree2BeanMutableLiveData().observe(this, tree2Bean -> {

            if (null != tree2Bean) {
                if (tree2Bean.getDatas().size() > 0) {
                    dataBind.statelayout.showSuccessView();
                    if (page == 0) {
                        stepList.clear();
                        stepList.addAll(tree2Bean.getDatas());
                    }
                } else {
                    if (page == 0) {
                        dataBind.statelayout.showEmptyView();
                    }
                }
            } else {
                dataBind.statelayout.showErrorView();
            }
            stepList.addAll(tree2Bean.getDatas());
            treeArticleAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tree2;
    }
}