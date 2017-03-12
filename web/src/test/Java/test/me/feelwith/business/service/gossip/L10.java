package test.me.feelwith.business.service.gossip;

import org.junit.Test;

/**
 * Created by Administrator on 2016/8/28.
 */
public class L10 {
    public boolean isMatch(String s, String p) {
        if(length(s) == 0 && length(p)== 0){
            return true;
        }
        int offset = 0;
        boolean isAny = false;
        int length = length(s);
        for(int i=0;i<p.length();i++){
            char ch = p.charAt(i);
            if(ch == '_'){
                offset += 1;
                if((offset )>length){
                    return false;
                }
            }else if(ch == '*'){
                isAny  = true;
            }else{
                if(offset == (length -1)){ //防止越界
                    return false;
                }
                int lastIndex = s.substring(offset).lastIndexOf(ch);
                if(lastIndex == -1){
                    return false;
                }
                isAny = false;
                offset = lastIndex;
            }
        }
        return true;
    }

    private int length(String s){
        if(s == null){return 0;};
        return s.length();
    }
    @Test
    public void t(){
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aaa","aa"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("aab","c*a*b"));
    }
}
