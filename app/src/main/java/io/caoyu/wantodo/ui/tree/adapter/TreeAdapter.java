package io.caoyu.wantodo.ui.tree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import io.caoyu.wantodo.api.bean.Tree2Bean;
import io.caoyu.wantodo.api.bean.TreeBean;
import io.caoyu.wantodo.databinding.ItemTreeBinding;
import io.caoyu.wantodo.ui.tree.Tree2Activity;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.ViewHolder> {


    private final List<TreeBean> stepList;
    private final LayoutInflater mInflater;
    private Context context;


    public TreeAdapter(Context mContext, List<TreeBean> stepList) {
        this.stepList = stepList;
        context = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(ItemTreeBinding.inflate(mInflater, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TreeBean dataBean = stepList.get(position);
        holder.binding.tvTitle.setText(dataBean.getName());

        Tree2Adapter tree2Adapter = new Tree2Adapter(context,dataBean.getChildren());

        tree2Adapter.setOnItemClickListener((pos, myLiveList) -> {
            TreeBean.ChildrenBean childrenBean = myLiveList.get(pos);
            Tree2Activity.show(context,childrenBean.getName(),childrenBean.getId());
        });
        FlexboxLayoutManager manager  = new FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP);
        holder.binding.recyclerView.setLayoutManager(manager);
        holder.binding.recyclerView.setAdapter(tree2Adapter);
    }

    @Override
    public int getItemCount() {
        if (stepList == null) {
            return 0;
        }
        return stepList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTreeBinding binding;

        public ViewHolder(ItemTreeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
