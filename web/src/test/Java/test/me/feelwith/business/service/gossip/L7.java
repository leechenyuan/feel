package test.me.feelwith.business.service.gossip;

import org.junit.Test;

/**
 * Created by Administrator on 2016/8/25.
 */
public class L7 {
    public int reverse(int x) {
        String prefix = x<0?"-":"";
        String result = prefix+new StringBuilder(String.valueOf(Math.abs(x))).reverse().toString();
        return Long.valueOf(result).intValue();
    }
    @Test
    public void t(){
        int i = 964632435;
        System.out.println(reverse(i));
    }
}
