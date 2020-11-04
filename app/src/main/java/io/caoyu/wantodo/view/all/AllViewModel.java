package io.caoyu.wantodo.view.all;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:54
 */
public class AllViewModel extends AndroidViewModel {

    public AllViewModel(@NonNull Application application) {
        super(application);
    }

    public List<String> getAllData(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        return list;
    }
}
