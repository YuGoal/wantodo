package io.yugoal.lib_common_ui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

/**
 * @user caoyu
 * @date 2019/11/23
 */
public class SpUtils {



    private static SharedPreferences sharedPreferences;

    /**
     * 输入框失去焦点时，保存输入框的值
     *
     * @param editText
     * @param key
     */
    public static void setEditVlaue(final EditText editText, final String key) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setValue(key, editText.getText().toString());
                }
            }
        });
    }

    public static String getValue(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static boolean getValue(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static int getValue(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static float getValue(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static void setValue(String key, String value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(key, value);
        e.apply();
    }

    public static void setValue(String key, int value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(key, value);
        e.apply();
    }

    public static void setValue(String key, float value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putFloat(key, value);
        e.apply();
    }

    public static void setValue(String key, boolean value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    public static void init(SharedPreferences sharedPreferences) {
        SpUtils.sharedPreferences = sharedPreferences;
    }

    public static void setVlaue(String password, String s) {

    }

}
