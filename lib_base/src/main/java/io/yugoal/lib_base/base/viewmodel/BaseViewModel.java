package io.yugoal.lib_base.base.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName:
 * @ClassName: BaseViewModel
 * @Description:
 * @Author: LiuTao
 * @CreateDate: 2020/5/9 13:49
 * @UpdateUser: LiuTao
 */
public class BaseViewModel extends AndroidViewModel {
    private Map<String, UnPeekLiveData> maps;

    /**
     * 构造函数（在ViewModelProvider里通过class.newInstance创建实例）
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
        //初始化集合(线程安全)
        maps = new ConcurrentHashMap<>();
    }

    /**
     * 通过指定的数据实体类获取对应的MutableLiveData类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> UnPeekLiveData<T> get(Class<T> clazz) {
        return get(null, clazz);
    }

    /**
     * 通过指定的key或者数据实体类获取对应的MutableLiveData类
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> UnPeekLiveData<T> get(String key, Class<T> clazz) {
        String keyName = "";
        if (TextUtils.isEmpty(key)) {
            keyName = clazz.getCanonicalName();
        } else {
            keyName = key;
        }
        UnPeekLiveData<T> mutableLiveData = maps.get(keyName);
        //1.判断集合是否已经存在livedata对象
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        //2.如果集合中没有对应实体类的Livedata对象，就创建并添加至集合中
        mutableLiveData = new UnPeekLiveData<T>();
        maps.put(keyName, mutableLiveData);
        return mutableLiveData;
    }

    /**
     * 在对应的FragmentActivity销毁之后调用
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void onCleared() {
        super.onCleared();
        if (maps != null) {
            maps.clear();
        }
    }



}
