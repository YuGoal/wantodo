package io.yugoal.lib_network.observer;




import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_base.base.model.MvvmBaseModel;
import io.yugoal.lib_network.errorhandler.ExceptionHandle;

public abstract class BaseObserver<T> implements Observer<T> {
    MvvmBaseModel baseModel;
    public BaseObserver(MvvmBaseModel baseModel) {
        this.baseModel = baseModel;
    }
    @Override
    public void onError(Throwable e) {
        if(e instanceof ExceptionHandle.ResponeThrowable){
            onFailure(e);
        } else {
            onFailure(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(baseModel != null){
            baseModel.addDisposable(d);
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
    public abstract void onFailure(Throwable e);
}
