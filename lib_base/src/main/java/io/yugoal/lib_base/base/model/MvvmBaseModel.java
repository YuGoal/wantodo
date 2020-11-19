package io.yugoal.lib_base.base.model;

import android.text.TextUtils;

import androidx.annotation.CallSuper;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.yugoal.lib_base.base.preference.BasicDataPreferenceUtil;
import io.yugoal.lib_base.base.utils.GsonUtils;
import io.yugoal.lib_utils.utils.ToastUtil;

/**
 * user caoyu
 * date 2020/11/18
 * time 9:59
 * model
 * 提供数据来源，通过接口回调返回数据。
 * 数据缓存，apk预制数据等。
 */
public abstract class MvvmBaseModel<T> {
    private CompositeDisposable compositeDisposable;
    protected ReferenceQueue<IBaseModelListener> mReferenceQueue;
    protected ConcurrentLinkedQueue<WeakReference<IBaseModelListener>> mWeakListenerArrayList;
    //缓存数据
    private BaseCachedData<T> mCachedData;

    protected boolean isRefresh = true;
    protected int pageNumber = 0;
    private boolean mIsPaging;
    //缓存key
    private String mCachedPreferenceKey;
    //apk预制key
    private String mApkPredefinedData;

    public MvvmBaseModel(boolean mIsPaging, String mCachedPreferenceKey, String mApkPredefinedData) {
        this.mIsPaging = mIsPaging;
        this.mCachedPreferenceKey = mCachedPreferenceKey;
        this.mApkPredefinedData = mApkPredefinedData;
        mReferenceQueue = new ReferenceQueue<>();
        mWeakListenerArrayList = new ConcurrentLinkedQueue<>();
        if (mCachedPreferenceKey != null) {
            mCachedData = new BaseCachedData<>();
        }
    }

    public boolean isPaging() {
        return mIsPaging;
    }


    /**
     * 注册监听
     *
     * @param listener 回调
     */
    public void register(IBaseModelListener listener) {
        if (null == listener) {
            return;
        }

        synchronized (this) {
            // 每次注册的时候清理已经被系统回收的对象
            Reference<? extends IBaseModelListener> releaseListener = null;
            while ((releaseListener = mReferenceQueue.poll()) != null) {
                mWeakListenerArrayList.remove(releaseListener);
            }

            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                IBaseModelListener listenerItem = weakListener.get();
                if (listenerItem == listener) {
                    return;
                }
            }
            WeakReference<IBaseModelListener> weakListener = new WeakReference<IBaseModelListener>(listener, mReferenceQueue);
            mWeakListenerArrayList.add(weakListener);
        }
    }

    /**
     * 取消监听
     *
     * @param listener
     */
    public void unRegister(IBaseModelListener listener) {

        if (listener == null) {
            return;
        }
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                IBaseModelListener listenerItem = weakListener.get();
                if (listener == listenerItem) {
                    mWeakListenerArrayList.remove(weakListener);
                    break;
                }
            }
        }
    }

    /**
     * 为了保证app打开的时候由于网络慢或者异常的情况下数据不为空，
     * 所以app对数据进行了预制；
     * 加载完成以后会立即进行网络请求，同时缓存在本地，今后app打开都会从preference读取，而不在读取
     * apk预制数据，由于数据变化没那么快，在app第二次打开的时候会生效，并且是一天请求一次。
     */
    protected void saveDataToPreference(T data) {
        mCachedData.data = data;
        mCachedData.updateTimeInMills = System.currentTimeMillis();
        BasicDataPreferenceUtil.getInstance().setString(mCachedPreferenceKey, GsonUtils.toJson(mCachedData));
    }

    public abstract void refresh();

    protected abstract void load();

    /**
     * 缓存的数据类型
     */
    public Type getTClass() {
        return null;
    }

    /**
     * 是否更新数据，可以在这里设计策略，可以是一天一次，一月一次等等，
     * 默认是每次请求都更新
     */
    protected boolean isNeedToUpdate() {
        return true;
    }

    @CallSuper
    public void cancel() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable d) {
        if (d == null) {
            return;
        }

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(d);
    }

    public void getCachedDataAndLoad() {
        if (mCachedPreferenceKey != null) {
            String saveDataString = BasicDataPreferenceUtil.getInstance().getString(mCachedPreferenceKey);
            if (TextUtils.isEmpty(saveDataString)) {
                try {
                    T savedData = GsonUtils.fromLocalJson(new JSONObject(saveDataString).get("data").toString(), getTClass());
                    if (null != savedData) {
                        if (isPaging()) {
                            //分页刷新
                            loadSuccess(savedData, new PagingResult(false, true, true));
                        } else {
                            //普通数据
                            loadSuccess(savedData);
                        }
                        if (isNeedToUpdate()) {
                            load();
                        }
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (mApkPredefinedData != null) {

            }
        }
        load();
    }

    /**
     * 发消息给UI线程
     */
    protected void loadSuccess(T data, PagingResult... pagingResult) {
        if (mIsPaging && (pagingResult == null || pagingResult.length < 1)) {
            return;
        }
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                if (weakListener.get() instanceof IBaseModelListener) {
                    IBaseModelListener listenerItem = weakListener.get();
                    if (listenerItem != null) {
                        if (pagingResult != null && pagingResult.length > 0) {
                            listenerItem.onLoadFinish(this, data, pagingResult);
                        } else {
                            listenerItem.onLoadFinish(this, data);
                        }
                    }
                }
            }
            if (mCachedPreferenceKey != null && pagingResult != null) {
                saveDataToPreference(data);
            }
        }
    }

    protected void loadFail(final String errorMessage, PagingResult... pagingResult) {
        if (mIsPaging && (pagingResult == null || pagingResult.length < 1)) {
            return;
        }
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                if (weakListener.get() instanceof IBaseModelListener) {
                    IBaseModelListener listenerItem = weakListener.get();
                    if (listenerItem != null) {
                        if (pagingResult != null && pagingResult.length > 0) {
                            listenerItem.onLoadFail(this, errorMessage, pagingResult);
                        } else {
                            listenerItem.onLoadFail(this, errorMessage);
                        }
                    }
                }
            }
        }
    }
}
