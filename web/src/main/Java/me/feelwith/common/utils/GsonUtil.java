package me.feelwith.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by L on 2016/5/12.
 */
public class GsonUtil {
    private static Gson gson =
            new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .create();
    public static String toJson(Object ob){
         return gson.toJson(ob);
    }
}
