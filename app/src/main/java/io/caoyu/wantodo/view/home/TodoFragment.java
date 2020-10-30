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

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.FragmentTodoLayoutBinding;
import io.caoyu.wantodo.event.ToDoListEvent;
import io.caoyu.wantodo.event.ToDoStatusEvent;
import io.caoyu.wantodo.model.ToDoBean;
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
    private List<ToDoBean> doBeanList = new ArrayList<>();
    private TodoAdapter todoAdapter;
    private Context mContext;

    private FragmentTodoLayoutBinding binding;

    private int page = 0;

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
        binding = FragmentTodoLayoutBinding.inflate(inflater,R.layout.fragment_todo_layout);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        binding.refreshLayout.setOnRefreshListener(this);
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        ToDoHelper.getToDoList(type, priority, orderby,page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTodoListEvent(ToDoListEvent event) {
        doBeanList.clear();
        binding.refreshLayout.finishRefresh();
        //待办清单
        doBeanList = event.getList();
        todoAdapter = new TodoAdapter(mContext, doBeanList);
        todoAdapter.setToDoCallBack(new TodoAdapter.ToDoCallBack() {
            @Override
            public void deleteToDo(int positon) {
                if (positon >= doBeanList.size()) {
                    return;
                }
                ToDoHelper.deleteToDo(doBeanList.get(positon));
                doBeanList.remove(positon);
                todoAdapter.notifyItemRemoved(positon);
            }

            @Override
            public void completedToDo(int positon) {
                if (positon >= doBeanList.size()) {
                    return;
                }
                ToDoBean doBean = doBeanList.get(positon);
                doBean.status =  doBean.status == 0 ? 1 : 0;
                ToDoHelper.updateToDo(doBean);
                doBeanList.remove(positon);
                todoAdapter.notifyItemRemoved(positon);
                EventBus.getDefault().post(new ToDoStatusEvent(true));
            }
        });
        ItemTouchHelper.Callback callback = new RecycleItemTouchHelper(todoAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
        binding.recyclerView.setAdapter(todoAdapter);
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
