package io.yugoal.lib_base.base.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.yugoal.lib_base.base.model.IBaseModelListener;
import io.yugoal.lib_base.base.model.MvvmBaseModel;
import io.yugoal.lib_base.base.model.PagingResult;
import io.yugoal.lib_utils.utils.ToastUtil;

public class MvvmBaseViewModel<M extends MvvmBaseModel, D> extends ViewModel implements LifecycleObserver, IBaseModelListener<List<D>> {
    private static final String TAG = "MvvmBaseViewModel";
    protected M model;

    public MutableLiveData<ObservableList<D>> dataList = new MutableLiveData<>();
    public MutableLiveData<ViewStatus> viewStatusLiveData = new MutableLiveData();
    public MutableLiveData<String> errorMessage = new MutableLiveData();


    public MvvmBaseViewModel() {
        dataList.setValue(new ObservableArrayList<D>());
        viewStatusLiveData.setValue(ViewStatus.LOADING);
        errorMessage.setValue("");
    }

    public void tryRefresh() {
        if (null != model) {
            model.refresh();
        }
    }

    @Override
    protected void onCleared() {
        if (model != null) {
            model.cancel();
        }
    }

    @Override
    public void onLoadFinish(MvvmBaseModel model, List<D> data, PagingResult... pageResult) {
        if (model == this.model) {
            if (model.isPaging()) {
                if (pageResult[0].isFirstPage) {
                    dataList.getValue().clear();
                }
                if (pageResult[0].isEmpty) {
                    if (pageResult[0].isFirstPage) {
                        viewStatusLiveData.postValue(ViewStatus.EMPTY);
                    } else {
                        viewStatusLiveData.postValue(ViewStatus.NO_MORE_DATA);
                    }
                } else {
                    dataList.getValue().addAll(data);
                    dataList.postValue(dataList.getValue());
                    viewStatusLiveData.postValue(ViewStatus.SHOW_CONTENT);
                }
            } else {
                dataList.getValue().clear();
                dataList.getValue().addAll(data);
                dataList.postValue(dataList.getValue());
                viewStatusLiveData.postValue(ViewStatus.SHOW_CONTENT);
            }
        }
    }

    @Override
    public void onLoadFail(MvvmBaseModel model, String prompt, PagingResult... pageResult) {
        errorMessage.setValue(prompt);
        if (model.isPaging() && !pageResult[0].isFirstPage) {
            viewStatusLiveData.setValue(ViewStatus.LOAD_MORE_FAILED);
        } else {
            viewStatusLiveData.setValue(ViewStatus.REFRESH_ERROR);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        dataList.postValue(dataList.getValue());
        viewStatusLiveData.postValue(viewStatusLiveData.getValue());
    }
}
