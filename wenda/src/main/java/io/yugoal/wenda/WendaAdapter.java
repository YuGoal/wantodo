package io.yugoal.wenda;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import io.yugoal.lib_base.base.customview.BaseCustomViewModel;
import io.yugoal.lib_base.base.recyclerview.BaseViewHolder;
import io.yugoal.wenda.ui.WendaItemModel;
import io.yugoal.wenda.ui.WendaItemView;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:57
 */
public class WendaAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ObservableList<WendaItemModel> list;

    public void setData(ObservableList<WendaItemModel> items) {
        list = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WendaItemView wendaItemView = new WendaItemView(parent.getContext());
        return new BaseViewHolder(wendaItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

}
