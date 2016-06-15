package me.feelwith.common.collection;

/**
 * Created by Administrator on 2016/6/9.
 *
 **/
public interface IFluentMap {
    int getInt (String key);
    int getInt(String key,int defaultValue);
    Integer getInteger(String key);
    Integer getInteger(String key,Integer defaultValue);


    String getString(String key);
    String getString(String key,String defaultValue);
}
