package io.yugoal.lib_utils.utils;


import java.io.File;

/**
 * 缓存辅助类
 * @author Cuizhen
 * @date 18/4/23
 */
public class CacheUtils {

    /**
     * 获取系统默认缓存文件夹
     * 优先返回SD卡中的缓存文件夹
     */
    public static String getCacheDir() {
        File cacheFile = null;
        if (FileUtils.isSDCardAvailable()) {
            cacheFile = UIUtils.getContext().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile =  UIUtils.getContext().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取系统默认缓存文件夹内的缓存大小
     */
    public static String getTotalCacheSize() {
        long cacheSize = FileUtils.getSize( UIUtils.getContext().getCacheDir());
        if (FileUtils.isSDCardAvailable()) {
            cacheSize += FileUtils.getSize( UIUtils.getContext().getExternalCacheDir());
        }
        return FileUtils.formatSize(cacheSize);
    }

    /**
     * 清除系统默认缓存文件夹内的缓存
     */
    public static void clearAllCache() {
        FileUtils.delete( UIUtils.getContext().getCacheDir());
        if (FileUtils.isSDCardAvailable()) {
            FileUtils.delete( UIUtils.getContext().getExternalCacheDir());
        }
    }

}