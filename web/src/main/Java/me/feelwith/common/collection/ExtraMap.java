package me.feelwith.common.collection;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/9.
 */
public class ExtraMap implements IFluentMap{
    private  Map data;

    private ExtraMap(Map data){
        this.data = data;
    }
    public static ExtraMap build(Map data){
        ExtraMap m = new ExtraMap(data);
        return m;
    }
    public  Map set(Object k,Object v){
        this.data.put(k,v);
        return this.data;
    }

    @Override
    public int getInt(String key) {
        return MapUtils.getIntValue(data,key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return MapUtils.getIntValue(data,key,defaultValue);
    }

    @Override
    public Integer getInteger(String key) {
        return MapUtils.getInteger(data,key);
    }

    @Override
    public Integer getInteger(String key, Integer defaultValue) {
        return MapUtils.getInteger(data,key,defaultValue);
    }

    @Override
    public String getString(String key) {
        return MapUtils.getString(data,key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return MapUtils.getString(data,key,defaultValue);
    }
}
