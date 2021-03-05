package io.yugoal.lib_utils.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * $activityName
 *
 * @author ${LiuTao}
 * @date 2018/4/15
 * 写文件的工具类
 * 1、内部存储
 * 常见就是我们的/data/data目录下的数据
 */
public class FileUtils {

    public static final String TAG = "FileUtils";

    /**
     * APP根目录 文件路径
     */
    public static String ROOT_DIR = "Android/data/" + UIUtils.getPackageName();
    private final static byte[] gSyncCode = new byte[0];



    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * /data/user/0/com.paiwujie.robredpacket/files
     *
     * @return
     */
    public static String getAppFilesDirPath() {
        String path = UIUtils.getContext().getFilesDir().getPath();
        if (createDirs(path)) {
            return path;
        } else {
            return "";
        }
    }

    /**
     * /data/user/0/com.paiwujie.robredpacket/cache
     *
     * @return
     */
    public static String getAppCacheDirPath() {
        String path = UIUtils.getContext().getCacheDir().getPath();
        if (createDirs(path)) {
            return path;
        } else {
            return "";
        }
    }

    /**
     * /storage/emulated/0/Android/data/com.paiwujie.robredpacket/cache
     *
     * @return
     */
    public static String getAppExternalCacheDir() {
        try {
            String path = UIUtils.getContext().getExternalCacheDir().getPath();
            if (createDirs(path)) {
                return path;
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * /storage/emulated/0/Android/data/com.paiwujie.robredpacket/files/test
     *
     * @return
     */
    public static String getAppExternalFilesDir() {
        String path = UIUtils.getContext().getExternalFilesDir("test").getPath();
        if (createDirs(path)) {
            return path;
        } else {
            return "";
        }
    }

    /**
     * 内部目录
     * 获取应用的cache目录
     * /data/user/0/com.paiwujie.robredpacket/cache/
     */
    public static String getFilesDir() {
        File f = UIUtils.getContext().getFilesDir();
        if (createDirs(f.getPath())) {
            return f.getPath() + "/";
        } else {
            return "";
        }
    }

    public static boolean isExists(String dirPath) {
        File file = new File(dirPath);
        if (file.exists() && !file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        Log.e("TAG", "filepath:" + file);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    public static boolean haseFile(File file) {
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 判断文件是否存在
     */
    public static boolean hasPath(String dirPath) {
        File file = new File(dirPath);
        if (file.exists() && !file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 复制文件，可以选择是否删除源文件
     */
    public static boolean copyFile(String srcPath, String destPath,
                                   boolean deleteSrc) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        return copyFile(srcFile, destFile, deleteSrc);
    }


    /***
     *  复制文件，可以选择是否删除源文件
     * @param srcFile 原文件
     * @param destFile  目标文件
     * @param deleteSrc 是否删除
     * @return
     */
    public static boolean copyFile(File srcFile, File destFile,
                                   boolean deleteSrc) {
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
            if (deleteSrc) {
                srcFile.delete();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            IOUtils.close(out);
            IOUtils.close(in);
        }
        return true;
    }

    /**
     * 判断文件是否可写
     */
    public static boolean isWriteable(String path) {
        try {
            if (StringUtils.isEmpty(path)) {
                return false;
            }
            File f = new File(path);
            return f.exists() && f.canWrite();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    /**
     * 修改文件的权限,例如"777"等
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 把数据写入文件
     *
     * @param is       数据流
     * @param path     文件路径
     * @param recreate 如果文件存在，是否需要删除重建
     * @return 是否写入成功
     */
    public static boolean writeFile(InputStream is, String path,
                                    boolean recreate) {
        boolean res = false;
        File f = new File(path);
        FileOutputStream fos = null;

        try {
            if (recreate && f.exists()) {
                f.delete();
            }
            if (!f.exists() && null != is) {
                File parentFile = new File(f.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(f);
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                res = true;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.close(fos);
            IOUtils.close(is);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(byte[] content, String path, boolean append) {
        boolean res = false;
        File f = new File(path);
        Log.e(TAG, f.getPath());
        RandomAccessFile raf = null;
        try {
            if (f.exists()) {
                if (!append) {
                    f.delete();
                    f.createNewFile();
                }
            } else {
                f.createNewFile();
            }
            if (f.canWrite()) {
                raf = new RandomAccessFile(f, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            res = false;
        } finally {
            IOUtils.close(raf);
        }
        return res;
    }

    /**
     * sdcard/pic/tanyang.jpg
     * <p>
     * toPrefix: tanyang
     * toSuffix: tanyang.jpg
     *
     * @param from
     * @param toPrefix
     * @param toSuffix
     * @return
     */

    public static File createOrRenameFile(File from, String toPrefix, String toSuffix) {
        File directory = from.getParentFile();
        if (!directory.exists()) {
            if (directory.mkdir()) {
            }
        }
        File newFile = new File(directory, toPrefix + toSuffix);
        for (int i = 1; newFile.exists() && i < Integer.MAX_VALUE; i++) {
            newFile = new File(directory, toPrefix + '(' + i + ')' + toSuffix);
        }
        if (!from.renameTo(newFile)) {
            return from;
        }
        return newFile;
    }


    /**
     * 获取文件的 toPrefix，toSuffix；
     * 1）假如我们文件的全路径是 sdcard/pic/tanyang.jpg ，那么 toPrefix 是 tanyang，toSuffix 是 。jpg
     * <p>
     * 2）假如我们文件的全路径是 sdcard/pic/tanyang ，那么 toPrefix 是 tanyang，toSuffix 是 空字符串。相当于 String toSuffix=”“;
     *
     * @param from fileInfo[0]: toPrefix;
     *             fileInfo[1]:toSuffix;
     * @return
     */
    public static String[] getFileInfo(File from) {
        String fileName = from.getName();
        int index = fileName.lastIndexOf(".");
        String toPrefix = "";
        String toSuffix = "";
        if (index == -1) {
            toPrefix = fileName;
        } else {
            toPrefix = fileName.substring(0, index);
            toSuffix = fileName.substring(index, fileName.length());
        }
        return new String[]{toPrefix, toSuffix};
    }

    public static String generateSuffix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }

    /**
     * 文件 重命名
     *
     * @param from
     * @return
     */
    public static File createFileWithCurDate(File from) {
        String[] fileInfo = getFileInfo(from);
        String toPrefix = "pwj" + generateSuffix();
        String toSuffix = fileInfo[1];
        File to = new File(from.getParent(), toPrefix + toSuffix);
        if (copy(from.getPath(), to.getPath(), false)) {
            return to;
        } else {
            return from;
        }


    }

    /**
     * 将文件转为byte[]
     *
     * @param file 文件
     * @return
     */
    public static byte[] getBytes(File file) {
        ByteArrayOutputStream out = null;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[(int) file.length()];
            int i = 0;
            while ((i = in.read(b)) != -1) {

                out.write(b, 0, b.length);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] s = out.toByteArray();
        return s;

    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path) {
        try {
            byte[] bytes = new byte[0];
            bytes = content.getBytes();
            return writeFile(bytes, path, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入 false 删除存在文件  true 在文件中继续添加
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path, boolean append) {
        byte[] bytes = new byte[0];
        try {
            bytes = content.getBytes();
            return writeFile(bytes, path, append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;


    }

    /**
     * 把键值对写入文件
     *
     * @param filePath 文件路径
     * @param key      键
     * @param value    值
     * @param comment  该键值对的注释
     */
    public static void writeProperties(String filePath, String key,
                                       String value, String comment) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);// 先读取文件，再把键值对追加到后面
            p.setProperty(key, value);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

    /**
     * 根据键读取值
     */
    public static String readProperties(String filePath, String key,
                                        String defaultValue) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            value = p.getProperty(key, defaultValue);

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.close(fis);
        }
        return value;
    }

    /**
     * 把字符串键值对的map写入文件
     */
    public static void writeMap(String filePath, Map<String, String> map,
                                boolean append, String comment) {
        if (map == null || map.size() == 0 || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            Properties p = new Properties();
            if (append) {
                fis = new FileInputStream(f);
                p.load(fis);// 先读取文件，再把键值对追加到后面
            }
            p.putAll(map);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

    /**
     * 把字符串键值对的文件读入map
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, String> readMap(String filePath,
                                              String defaultValue) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            IOUtils.close(fis);
        }
        return map;
    }

    /**
     * 改名
     */
    /**
     * @param src
     * @param des
     * @param delete
     * @return
     */
    public static boolean copy(String src, String des, boolean delete) {
        Log.e("文件上传 源文件,", src);
        Log.e("文件上传 复制文件,", des);
        File file = new File(src);
        if (!file.exists()) {
            return false;
        }
        //复制的文件
        File desFile = new File(des);
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            IOUtils.close(in);
            IOUtils.close(out);
        }
        if (delete) {
            file.delete();
        }
        return true;
    }

    /***删除文件
     *
     * @param filepath
     * @param name
     */
    public static void deleteFile(String filepath, String name) {
        File file = new File(filepath + name);
        if (filepath == null && name == null) {
            return;
        }
        if (file.exists()) {
            file.delete();
        }

    }

    /**
     * delete file or directory
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {

        synchronized (gSyncCode) {
            if (TextUtils.isEmpty(path)) {
                return true;
            }

            File file = new File(path);
            if (!file.exists()) {
                return true;
            }
            if (file.isFile()) {
                return file.delete();
            }
            if (!file.isDirectory()) {
                return false;
            }
            File[] filesList = file.listFiles();

            if (filesList != null) {
                for (File f : filesList) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        deleteFile(f.getAbsolutePath());
                    }
                }
            }

            return file.delete();
        }

    }

    /***删除目标文件
     *
     * @param filepath
     */
    public static void deleteTargetFile(String filepath) {
        if (TextUtils.isEmpty(filepath)) {
            return;
        }
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }

    }

    /***删除文件夹和文件夹里面的文件
     *
     * @param pPath
     */
    public static void deleteDir(final String pPath) {
        if (TextUtils.isEmpty(pPath)) {
            return;
        }
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete(); // 删除所有文件
            } else if (file.isDirectory()) {
                deleteDirWihtFile(file); // 递规的方式删除文件夹
            }
        }
        dir.delete();// 删除目录本身
    }

    public static String readFileText(String pathfile) {
        if (TextUtils.isEmpty(pathfile)) {
            return "";
        }
        File f = new File(pathfile);
        return readFileText(f);
    }

    /**
     * 读取文件内容
     *
     * @param pathfile
     * @param name
     * @return
     */
    public static String readFileText(String pathfile, String name) {
        if (TextUtils.isEmpty(pathfile)) {
            return "";
        }
        File f = new File(pathfile + name);
        return readFileText(f);
    }

    /**
     * 读取文件内容
     *
     * @return
     */
    public static String readFileText(File f) {
        if (!f.exists()) {
            return "";
        }
        FileInputStream is;
        String result = null;
        try {
            is = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len = -1;
            while ((len = is.read(array)) > 0 - 1) {
                bos.write(array, 0, len);
            }
            byte[] data = bos.toByteArray(); // 取内存中保存的数据
            result = new String(data);
            bos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteMissionFile(String pathfile, String name) {
        File f = new File(pathfile + name);
        if (f.exists()) {
            return f.delete();
        } else {
            return true;
        }
    }

    /***
     * 获取指定路径下的文件目录
     */
    private static void getFilePath() {

    }

    /***
     * 读取指定目录下的文件
     * @param strPath
     * @return
     */
    public static ArrayList<String> readFileListNames(String strPath) {
        ArrayList<String> fileLists = new ArrayList<>();
        String filename;//文件名
        String suf;//文件后缀
        File dir = new File(strPath);//文件夹dir
        Log.e("fileUtils", "readFileListNames:" + strPath);
        File[] files = dir.listFiles();//文件夹下的所有文件或文件夹

        if (files == null) {
            return fileLists;
        }

        for (int i = 0; i < files.length; i++) {

            if (files[i].isDirectory()) {
//                System.out.println("---" + files[i].getAbsolutePath());
//                readFileListNames(files[i].getAbsolutePath());//递归文件夹！！！
            } else {
                filename = files[i].getName();
                fileLists.add(filename);//对于文件才把它的路径加到filelist中
            }

        }
        return fileLists;
    }

    /***
     * 获取指定工程目录下所有目录
     * @param strPath
     * @return
     */
    public static ArrayList<String> readFileLists(String strPath) {
        ArrayList<String> fileLists = new ArrayList<>();
        try {
            String filename;//文件名
            Log.e("readFileListNames:", strPath);
            File dir = new File(strPath);//文件夹dir
            if (!dir.exists() || !dir.isDirectory()) {
                // dir.mkdirs();
                return fileLists;
            }
            Log.e("文件夹名字:", dir.getName());
            File[] files = dir.listFiles();//文件夹下的所有文件或文件夹
            if (files == null) {
                return fileLists;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) { //是一个目录
                    fileLists.add(files[i].getName());
                }
            }
        } catch (Exception e) {
            Log.e("readFileLists", e.toString());
            e.printStackTrace();
        }
        return fileLists;
    }

    /**
     * 文件的写入
     * 传入一个文件的名称和一个Bitmap对象
     * 最后的结果是保存一个图片
     */
    public static void comparesBitmap(String path, String key, Bitmap bitmap) {

        try {
            File file = new File(path + key);
            if (!file.exists()) {
                file.createNewFile();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
            int options = 100;
            while (baos.toByteArray().length / 1024 > 1024) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
                baos.reset();// 重置baos即清空baos
                options -= 10;// 每次都减少10
                if (options < 0) {
                    options = 5;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    break;
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

            }
            FileOutputStream out = new FileOutputStream(file);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(file);
            UIUtils.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
    }

    /**
     * 压缩照片
     *
     * @param path  要保存的文件路径
     * @param image
     */
    public static Bitmap compressBitmap(String path, Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 90, baos);
        int options = 100;
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 100) {
            // 重置baos即清空baos
            baos.reset();
            // 每次都减少10
            options -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, null);
        try {
            FileOutputStream out = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 本地文件压缩
     *
     * @param path
     * @param onSaveSuccessListener
     * @return
     */
    public static void compressFile(String path, OnSaveSuccessListener onSaveSuccessListener) {
        String compressdPicPath = "";
//      ★★★★★★★★★★★★★★重点★★★★★★★★★★★★★
      /*  //★如果不压缩直接从path获取bitmap，这个bitmap会很大，下面在压缩文件到100kb时，会循环很多次，
        // ★而且会因为迟迟达不到100k，options一直在递减为负数，直接报错
        //★ 即使原图不是太大，options不会递减为负数，也会循环多次，UI会卡顿，所以不推荐不经过压缩，直接获取到bitmap
        Bitmap bitmap=BitmapFactory.decodeFile(path);*/
//      ★★★★★★★★★★★★★★重点★★★★★★★★★★★★★
        Bitmap bitmap = decodeSampledBitmapFromPath(path, 720, 1280);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        /* options表示 如果不压缩是100，表示压缩率为0。如果是70，就表示压缩率是70，表示压缩30%; */
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        while (baos.toByteArray().length / 1024 > 100) {
// 循环判断如果压缩后图片是否大于500kb继续压缩
            baos.reset();
            options -= 10;
            if (options < 11) {//为了防止图片大小一直达不到200kb，options一直在递减，当options<0时，下面的方法会报错
                // 也就是说即使达不到200kb，也就压缩到10了
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                break;
            }
// 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }

        String mDir = getAppFilesDirPath();
        File dir = new File(mDir);
        if (!dir.exists()) {
            dir.mkdirs();//文件不存在，则创建文件
        }
        File file = new File(mDir, System.currentTimeMillis() + ".jpg");
        FileOutputStream fOut = null;
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            if (onSaveSuccessListener != null) {
                onSaveSuccessListener.onSuccess(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据图片要显示的宽和高，对图片进行压缩，避免OOM
     *
     * @param path
     * @param width  要显示的imageview的宽度
     * @param height 要显示的imageview的高度
     * @return
     */
    private static Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {

//      获取图片的宽和高，并不把他加载到内存当中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = caculateInSampleSize(options, width, height);
//      使用获取到的inSampleSize再次解析图片(此时options里已经含有压缩比 options.inSampleSize，再次解析会得到压缩后的图片，不会oom了 )
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;

    }

    /**
     * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
     *
     * @param options
     * @param reqWidth  要显示的imageview的宽度
     * @param reqHeight 要显示的imageview的高度
     * @return
     * @compressExpand 这个值是为了像预览图片这样的需求，他要比所要显示的imageview高宽要大一点，放大才能清晰
     */
    private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (width >= reqWidth || height >= reqHeight) {

            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(width * 1.0f / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);

        }

        return inSampleSize;
    }

    /**
     * 文件的写入
     * 传入一个文件的名称和一个Bitmap对象
     * 最后的结果是保存一个图片
     */
    public static void saveToSDCard(String path, Bitmap bitmap) {

        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            FileOutputStream out = new FileOutputStream(file);
            out.write(baos.toByteArray());
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(file);
            UIUtils.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        recycleBitmap(bitmap);
    }

    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps == null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

    /***
     * 保存到SD卡
     * @param path
     * @param key
     * @param bitmap
     */
    public static void saveBitMapToSdCard(String path, String key, Bitmap bitmap) {
        FileOutputStream fos;
        try {
            File files = new File(path);
            if (!files.exists()) {
                files.mkdir();
            }
            File file = new File(path + key);
            fos = new FileOutputStream(file);
            //保存图片的设置，压缩图片
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();//关闭流
            bitmap.recycle();
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            UIUtils.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件的读取，
     * 根据文件的名字，读取出一个Bitmap的对象，
     * 如果之前保存过就有值，否则是null
     */
    public static Bitmap readFromSDCard(String path, String key) {
        return BitmapFactory.decodeFile(path + key);
    }

    /**
     * 压缩图片
     *
     * @param bitmap
     * @return
     */
    public static byte[] createBitmapThumbnail(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 32) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            if (options < 0) {
                options = 5;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                break;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

        }
        bitmap.recycle();
        return baos.toByteArray();
    }


    public static void delete(File file, String except) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String c : children) {
                File childFile = new File(file, c);
                if (!TextUtils.equals(childFile.getName(), except)) {
                    delete(childFile);
                }
            }
        } else {
            if (!TextUtils.equals(file.getName(), except)) {
                file.delete();
            }
        }
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String c : children) {
                boolean success = delete(new File(file, c));
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static long getSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                if (f.isDirectory()) {
                    size = size + getSize(f);
                } else {
                    size = size + f.length();
                }
            }
        } catch (Exception ignore) {
        }
        return size;
    }

    /**
     * 格式化单位
     */
    public static String formatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /***
     * 保存到SD卡
     * @param path
     * @param filename
     * @param bitmap
     */
    public static void savebitMapToSDCardJPG(String path, String filename, Bitmap bitmap) {
        Log.e("媒体文件", path);
        FileOutputStream fos;
        try {
            File files = new File(path);
            if (!files.getParentFile().exists()) {
                files.getParentFile().mkdirs();
            }
            if (!files.exists()) {
                files.mkdir();
            }
            File file = new File(path + filename);
            fos = new FileOutputStream(file);
            //保存图片的设置，压缩图片
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();//关闭流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件内容
     *
     * @return
     */
    public static String readKMLtext(File f) {
        if (!f.exists()) {
            return null;
        }
        FileInputStream is;
        String result = null;
        try {
            is = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len = -1;
            while ((len = is.read(array)) > 0 - 1) {
                bos.write(array, 0, len);
            }
            byte[] data = bos.toByteArray(); // 取内存中保存的数据
            result = new String(data, "UTF-8");
            bos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 重命名文件
     *
     * @param oldPath 原来的文件地址
     * @param newPath 新的文件地址
     */
    public static void renameFile(String oldPath, String newPath) {
        File oleFile = new File(oldPath);
        File newFile = new File(newPath);
        //执行重命名
        oleFile.renameTo(newFile);
    }

    public static String createFile(String projectname) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getKMLExternalStoragePath(projectname));
        } else {
            sb.append(getFilesDir());
        }
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return "";
        }
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getKMLExternalStoragePath(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(name);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 得到SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息

                if (lineStr.contains("sdcard")
                        && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure",
                                "");
                        return result;
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0) {
                    p.exitValue();
                }// p.exitValue()==0表示正常结束，1：非正常结束
            }
            inBr.close();
            in.close();
        } catch (Exception e) {


            return Environment.getExternalStorageDirectory().getPath();
        }

        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 按文件名排序
     *
     * @param files
     */
    public static List<File> orderByName(File[] files) {

        List<File> fileList = Arrays.asList(files);

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                }
                if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
        return fileList;
    }


    public interface OnSaveSuccessListener {
        void onSuccess(String path);
    }
}
