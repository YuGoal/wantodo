package io.yugoal.lib_base.base.model;

/**
 * user caoyu
 * date 2020/11/18
 * time 10:00
 */
public interface IBaseModelListener<T> {
    void onLoadFinish(MvvmBaseModel model, T data, PagingResult... pageResult);

    void onLoadFail(MvvmBaseModel model, String prompt, PagingResult... pageResult);
}
