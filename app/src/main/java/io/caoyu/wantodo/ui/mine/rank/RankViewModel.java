package io.caoyu.wantodo.ui.mine.rank;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import io.caoyu.wantodo.api.RetrofitClient;
import io.caoyu.wantodo.api.bean.ArticleBean;
import io.caoyu.wantodo.api.bean.CoinBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_network.okhttp.ResultBean;
import io.yugoal.lib_network.okhttp.RxObservableTransformer;
import retrofit2.Response;

public class RankViewModel extends AndroidViewModel {

    private MutableLiveData<CoinBean> coinBeanMutableLiveData;

    public MutableLiveData<CoinBean> getCoinBeanMutableLiveData() {
        return coinBeanMutableLiveData;
    }

    public RankViewModel(@NonNull Application application) {
        super(application);
        coinBeanMutableLiveData = new MutableLiveData<>();
    }

    public void getCoinCount() {
        RetrofitClient.getRetrofit().getRetrofitApi().coinCount()
                .compose(RxObservableTransformer.transformer())
                .subscribe(new Observer<Response<ResultBean<CoinBean>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Response<ResultBean<CoinBean>> resultBeanResponse) {
                        if (resultBeanResponse.isSuccessful()) {
                            coinBeanMutableLiveData.postValue(resultBeanResponse.body().getData());
                        } else {
                            coinBeanMutableLiveData.postValue(null);
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
