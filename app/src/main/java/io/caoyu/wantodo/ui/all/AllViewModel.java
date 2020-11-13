package io.caoyu.wantodo.ui.all;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import io.caoyu.wantodo.api.RetrofitClient;
import io.caoyu.wantodo.api.bean.ArticleBean;
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
public class AllViewModel extends AndroidViewModel {

    private MutableLiveData<ArticleBean> articleBeanMutableLiveData;

    public MutableLiveData<ArticleBean> getArticleBeanMutableLiveData() {
        return articleBeanMutableLiveData;
    }

    public AllViewModel(@NonNull Application application) {
        super(application);
        articleBeanMutableLiveData = new MutableLiveData<>();
    }

    public void getAllData() {
        RetrofitClient.getRetrofit().getRetrofitApi().article(0)
                .compose(RxObservableTransformer.transformer())
                .subscribe(new Observer<Response<ResultBean<ArticleBean>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Response<ResultBean<ArticleBean>> resultBeanResponse) {
                        if (resultBeanResponse.isSuccessful()) {
                            articleBeanMutableLiveData.postValue(resultBeanResponse.body().getData());
                        } else {
                            articleBeanMutableLiveData.postValue(null);
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
