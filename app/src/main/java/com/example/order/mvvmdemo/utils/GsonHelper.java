package com.example.order.mvvmdemo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lh, 2020-04-01
 */
public class GsonHelper {

    /**
     * 转成HashMap
     * @param originStr
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap toHashMap(String originStr) {
        HashMap<K, V> map = new Gson().fromJson(originStr, new TypeToken<HashMap<K, V>>() {
        }.getType());

        return map;
    }

    /**
     * 转成list
     * @param originStr
     * @param <T>
     * @return
     */
    public static <T> List toList(String originStr, Class<T> clazz) {
        Gson gson = new Gson();
        ArrayList<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(originStr).getAsJsonArray();
        if (array == null || array.size() <= 0) return null;

        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, clazz));
        }
        return list;
    }

    /**
     * 转成目标对象
     * @param originStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toClass(String originStr, Class<T> clazz) {
        T obj = null;
        try {
            obj = new Gson().fromJson(originStr, clazz);
        }catch (Exception e) {
            LogUtil.w(e.toString());
        } finally {
            return obj;
        }
    }

    /**
     * 对象转成string
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return new Gson().toJson(obj);
    }
}
