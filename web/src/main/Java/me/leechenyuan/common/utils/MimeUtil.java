package me.leechenyuan.common.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * * Created by L on 2016/5/13 16:46.
 */
public class MimeUtil {
    public static String getMimeType(String filePath){
        if(!StringUtils.contains(filePath,".")){
           throw new IllegalArgumentException("can't identify mime type from path : "+filePath);
        }
        return filePath.substring(filePath.lastIndexOf(".")+1);
    }
}
