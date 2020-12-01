package io.yugoal.tree.treelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.tree.R;
import io.yugoal.tree.databinding.ActivityTreeListBinding;

public class TreeListActivity extends MvvmActivity<ActivityTreeListBinding, TreeListViewModel> {
    private static final String TAG = "TreeListActivity";
    private TreeRecyclerViewAdapter treeRecyclerViewAdapter;

    public static void show(Context context, int cid, String title) {
        Intent intent = new Intent(context, TreeListActivity.class);
        intent.putExtra("cid", cid);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (null != intent) {
            int cid = intent.getIntExtra("cid", 0);
            String title = intent.getStringExtra("title");
            initToolbar(title);
            addDetailFragment(savedInstanceState, cid);
        }

    }

    private void addDetailFragment(Bundle savedInstanceState, int cid) {
        //添加Fragment应以这种方式,当旋转屏幕和被系统杀掉重启时,才不会出问题
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager(); //获取Fragment管理器对象
            FragmentTransaction ft = fm.beginTransaction(); //开启事务
            TreeListFragment treeListFragment;
            if (fm.findFragmentById(R.id.frame) == null) {
                treeListFragment = new TreeListFragment();
            } else {
                treeListFragment = (TreeListFragment) fm.findFragmentById(R.id.frame);
                ft.remove(treeListFragment);
                fm.popBackStack();
                ft.commit();
                ft = fm.beginTransaction();
            }
            //传入一些参数
            Bundle bundle = new Bundle();
            bundle.putInt("cid", cid);
            treeListFragment.setArguments(bundle);

            ft.add(R.id.frame, treeListFragment); //将fragment添加到布局当中
            ft.commit(); //千万别忘记提交事务
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tree_list;
    }

    @Override
    protected TreeListViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}