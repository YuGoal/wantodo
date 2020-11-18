package io.yugoal.lib_base.base.model;

import java.io.Serializable;

/**
 * user caoyu
 * date 2020/11/18
 * time 10:09
 */
public class BaseCachedData<T> implements Serializable {
    public T data;
    public long updateTimeInMills;
}
