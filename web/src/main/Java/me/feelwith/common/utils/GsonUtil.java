package me.feelwith.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by L on 2016/5/12.
 */
public class GsonUtil {
    private static Gson gson = new GsonBuilder().create();
    public static String toJson(Object ob){
         return gson.toJson(ob);
    }
}
