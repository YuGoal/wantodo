package io.caoyu.wantodo.ui.tree;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.caoyu.wantodo.api.bean.Tree2Bean;
import io.caoyu.wantodo.api.bean.TreeBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_network.okhttp.ResultBean;
import io.yugoal.lib_network.okhttp.RxObservableTransformer;
import retrofit2.Response;

/**
 * user caoyu
 * date 2020/11/4
 * time 15:54
 */
public class TreeViewModel extends AndroidViewModel {

    private MutableLiveData<List<TreeBean>> treeBeanMutableLiveData;
    private MutableLiveData<Tree2Bean> tree2BeanMutableLiveData;

    public MutableLiveData<List<TreeBean>> getTreeBeanMutableLiveData() {
        return treeBeanMutableLiveData;
    }

    public MutableLiveData<Tree2Bean> getTree2BeanMutableLiveData() {
        return tree2BeanMutableLiveData;
    }

    public TreeViewModel(@NonNull Application application) {
        super(application);
        treeBeanMutableLiveData = new MutableLiveData<>();
        tree2BeanMutableLiveData = new MutableLiveData<>();
    }

    public void getTree() {
        RetrofitClient.getRetrofit().getRetrofitApi().tree()
                .compose(RxObservableTransformer.transformer())
                .subscribe(new Observer<Response<ResultBean<List<TreeBean>>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Response<ResultBean<List<TreeBean>>> resultBeanResponse) {
                        if (resultBeanResponse.isSuccessful()) {
                            if (resultBeanResponse.body() != null) {
                                treeBeanMutableLiveData.postValue(resultBeanResponse.body().getData());
                            }
                        } else {
                            treeBeanMutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTree2(int page,int cid) {
        RetrofitClient.getRetrofit().getRetrofitApi().tree2(page,cid)
                .compose(RxObservableTransformer.transformer())
                .subscribe(new Observer<Response<ResultBean<Tree2Bean>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Response<ResultBean<Tree2Bean>> resultBeanResponse) {
                        if (resultBeanResponse.isSuccessful()) {
                            if (resultBeanResponse.body() != null) {
                                tree2BeanMutableLiveData.postValue(resultBeanResponse.body().getData());
                            }
                        } else {
                            tree2BeanMutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
