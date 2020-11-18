package io.yugoal.lib_common_ui.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @user caoyu
 * @date 2019/11/12
 */
public class GsonUtil {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
            .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
            .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
            .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
            .registerTypeAdapter(Long.class, new LongDefault0Adapter())
            .registerTypeAdapter(long.class, new LongDefault0Adapter())
            .create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonParseException {
        return (T) gson.fromJson(json, typeOfT);
    }
}
