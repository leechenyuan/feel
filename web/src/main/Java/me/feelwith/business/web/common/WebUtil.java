package me.feelwith.business.web.common;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/9.
 */
public class WebUtil {
    public static int getUserId(HttpServletRequest request){
        //FIXME
        return 0;
    }

    public static Map getSimpleParams(HttpServletRequest request){
        Map<String,Object> params = Maps.newHashMap();
        Enumeration<String> keys = request.getParameterNames();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            params.put(key,request.getParameter(key));
        }
        return  params;
    }

    public static Timestamp getSqlDateTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
