package com.sunshine.reptile.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;
import java.util.Map;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 11:52
 */
public class JsonUtils {
    private static final ThreadLocal<Gson> gsonLocal = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new  GsonBuilder().create();
        }
    };
    public static String toJson(Object obj){
        Gson gson = gsonLocal.get();
        return gson.toJson(obj);
    }

    public  static <T> T toBean(String json,Class<T> clz){
        Gson gson = gsonLocal.get();
        return gson.fromJson(json, clz);
    }

    public static <T> T toBean4Esearch(String json, Class<T> clz) {
        json = StringEscapeUtils.unescapeJson(json);
        json = json.substring(1, json.length() - 1);
        Gson gson = gsonLocal.get();
        return gson.fromJson(json, clz);
    }

    public  static List<?> toList(String json){
        Gson gson = gsonLocal.get();
        return gson.fromJson(json, new TypeToken<List<?>>(){}.getType());
    }

    public static Map<?, ?> toMap(String json){
        Gson gson = gsonLocal.get();
        return gson.fromJson(json, new TypeToken<Map<?, ?>>(){}.getType());
    }

    /**
     * Google Gson 验证是否为合法JSON字符串
     * @param jsonInString
     * @return
     */
    public final static boolean isJSONValid(String jsonInString) {
        try {
            new Gson().fromJson(jsonInString, Object.class);
            return true;
        } catch(JsonSyntaxException ex) {
            return false;
        }
    }

}
