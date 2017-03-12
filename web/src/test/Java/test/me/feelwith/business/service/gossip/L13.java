package test.me.feelwith.business.service.gossip;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/21.
 */
public class L13 {
    public int romanToInt(String s) {
        int value = 0;
        for(int i=0;i<s.length();i++){
            int nextI = i+1;
            int a = getValue(s.charAt(i));
            int b = 0;
            if(nextI<s.length()){
                b =  getValue(s.charAt(nextI));
                if(b>a){
                    a = b-a;
                    i++;
                }
            }
            value+=a;
        }
        return value;
    }

    public static int getValue(char c){
        switch (c){
            case 'I':return  1;
            case 'V':return  5;
            case 'X':return 10;
            case 'L':return 50;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
            default:throw new IllegalArgumentException();
        }
    }

    @Test
    public void t(){
//        Assert.assertEquals(romanToInt("I"),1);
//        Assert.assertEquals(romanToInt("XIX"),19);
//        Assert.assertEquals(romanToInt("XIX"),11);
        Assert.assertEquals(3999,romanToInt("MMMCMXCIX"));
        Assert.assertEquals(701,romanToInt("DCC"));
    }
}
