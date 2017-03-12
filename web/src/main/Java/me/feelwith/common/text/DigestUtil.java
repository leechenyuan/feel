package me.feelwith.common.text;

import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

/**
 * Created by L on 2017/2/11.
 */
public class DigestUtil {
    private static final String CHAR_SET = "UTF-8";
    public static String dm5Hex(String text){
        return DigestUtils.md5DigestAsHex(text.getBytes(Charset.forName(CHAR_SET))) ;
    }
}
