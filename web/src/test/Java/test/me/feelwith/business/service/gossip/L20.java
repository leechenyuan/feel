package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.Deque;

/**
 * Created by L on 2016/12/8.
 */
public class L20 {
    public boolean isValid(String s) {
        if(s == null  || s.length()%2!=0){
            return false;
        }
        char [] text = s.toCharArray();
        int offset = 0;
        text[0] = s.charAt(0);
        for(int i=1;i<s.length();i++){
            char ch = s.charAt(i);
            if(isEndChar(ch, chars)){
                if(isMatch(text[offset],ch)){
                    offset-- ;
                }else{
                    return  false;
                }
            }else{
                text[++offset] = ch;
            }
        }
        return offset== -1;
    }

    char [] chars = new char[]{')',']','}'};
    private static boolean isEndChar(char target,char [] chars){
        for(char c : chars){
            if(target == c){
                return  true;
            }
        }
        return false;
    }

    private static boolean isMatch(char a,char b){
        if(a == '('){
            return b == ')';
        }else if(a == '{'){
            return b == '}';
        }else{
            return b == ']';
        }
    }


    @Test
    public void t(){
        String s =
//                "()"
//                "()[]{}"
//                "(]"
                "([)]"
                ;
        System.out.println(isValid(s));
    }
}
