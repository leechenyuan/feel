package me.feelwith.business.web.result;

/**
 * Created by Administrator on 2016/6/5.
 */
public class Result<T> {
    int code;
    String msg;
    T value;
    public Result(int code,String msg,T value){
        this.code = code ;
        this.msg = msg;
        this.value = value;
    }
}
