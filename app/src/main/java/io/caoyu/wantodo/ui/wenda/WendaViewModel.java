package io.caoyu.wantodo.ui.wenda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import io.caoyu.wantodo.api.bean.WendaBean;
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
public class WendaViewModel extends AndroidViewModel {

    private MutableLiveData<WendaBean> wendaBeanMutableLiveData;

    public MutableLiveData<WendaBean> getWendaBeanMutableLiveData() {
        return wendaBeanMutableLiveData;
    }

    public WendaViewModel(@NonNull Application application) {
        super(application);
        wendaBeanMutableLiveData = new MutableLiveData<>();
    }

    public void getWendaData(int page) {
        RetrofitClient.getRetrofit().getRetrofitApi().wenda(page)
                .compose(RxObservableTransformer.transformer())
                .subscribe(new Observer<Response<ResultBean<WendaBean>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Response<ResultBean<WendaBean>> resultBeanResponse) {
                        if (resultBeanResponse.isSuccessful()) {
                            wendaBeanMutableLiveData.postValue(resultBeanResponse.body().getData());
                        } else {
                            wendaBeanMutableLiveData.postValue(null);
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
