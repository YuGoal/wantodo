package io.caoyu.wantodo.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.caoyu.wantodo.R;
import io.caoyu.wantodo.event.ToDoListEvent;
import io.caoyu.wantodo.event.ToDoStatusEvent;
import io.caoyu.wantodo.db.ToDoBean;
import io.caoyu.wantodo.repository.ToDoHelper;
import io.caoyu.wantodo.view.home.adapter.RecycleItemTouchHelper;
import io.caoyu.wantodo.view.home.adapter.TodoAdapter;

/**
 * @user caoyu
 * @date 2019/11/13
 * 未完成清单
 */
public class TodoFragment extends Fragment implements OnRefreshListener {
    private static final String TAG = "TodoFragment";
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;

    private List<ToDoBean> doBeanList = new ArrayList<>();
    private TodoAdapter todoAdapter;
    private Context mContext;

    private int status = 0;//1完成；0未完成; 默认全部展示；
    private int type;//创建时传入的类型, 默认全部展示
    private int priority;//创建时传入的优先级；默认全部展示
    private int orderby = 4;//1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；

    public static Fragment newInstance() {
        TodoFragment fragment = new TodoFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View rootView = inflater.inflate(R.layout.fragment_todo_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        refreshLayout.setOnRefreshListener(this);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        ToDoHelper.getToDoList(type, priority, orderby);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTodoListEvent(ToDoListEvent event) {
        doBeanList.clear();
        refreshLayout.finishRefresh();
        //待办清单
        doBeanList = event.getList();
        todoAdapter = new TodoAdapter(mContext, doBeanList);
        todoAdapter.setToDoCallBack(new TodoAdapter.ToDoCallBack() {
            @Override
            public void deleteToDo(int positon) {
                if (positon >= doBeanList.size()) {
                    return;
                }
                ToDoHelper.deleteToDo(doBeanList.get(positon).getId());
                doBeanList.remove(positon);
                todoAdapter.notifyItemRemoved(positon);
            }

            @Override
            public void completedToDo(int positon) {
                if (positon >= doBeanList.size()) {
                    return;
                }
                ToDoBean doBean = doBeanList.get(positon);
                ToDoHelper.completedToDo(doBean.getId(), doBean.getStatus() == 0 ? 1 : 0);
                doBeanList.remove(positon);
                todoAdapter.notifyItemRemoved(positon);
                EventBus.getDefault().post(new ToDoStatusEvent(true));
            }
        });
        ItemTouchHelper.Callback callback = new RecycleItemTouchHelper(todoAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
        recyclerview.setAdapter(todoAdapter);
        todoAdapter.notifyDataSetChanged();
    }

    /**
     * 有清单待完成，刷新数据
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTodoEvent(ToDoStatusEvent event) {
        requestData();
    }
}
