package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/28.
 */
public class L10Original {
    public boolean isMatch(String s, String p) {
        Set<Integer> indexes = new LinkedHashSet<>();
        indexes.add(0);
        for(int i=0;i<p.length();i++){
            Set<Integer> nextIndexes = new LinkedHashSet<>();
            char pc = p.charAt(i);
            boolean isAnyone = false;
            int nextI = i+1;
            if(nextI < p.length() && ('*'==p.charAt(nextI))){
                isAnyone = true;
                i++;
            }
            if(!isAnyone){
                for(int j : indexes){
                    if(j >= s.length()){
                        break;
                    }
                    if(isMatchChar(pc,s.charAt(j))){
                        nextIndexes.add(j+1);
                    }
                }
            }else{
                for(int j : indexes){
                    if(j>=s.length()){
                        nextIndexes.add(j);
                        break;
                    }
                    int n=j;
                    while(isMatchChar(pc,s.charAt(n)) ){
                        nextIndexes.add(n);
                        n++;
                        if(n>=s.length()){
                            break;
                        }
                    }
                    nextIndexes.add(n);//加上下一个
                }
            }

            if(nextIndexes.isEmpty()){
                return false;
            }
            indexes = nextIndexes;
        }
        for(int i : indexes){
            if(i >= s.length()){
                return true;
            }
        }
        return  false;
    }
    private static boolean isMatchChar(char p ,char c){
        return (p == '.' ) || (p == c);
    }
    @Test
    public void t(){
        System.out.println(isMatch("aasdfasdfasdfasdfas","aasdf.*asdf.*asdf.*asdf.*s"));
        System.out.println(isMatch("aasdfasdfasdfasdfas","aasdf.*asdf.*asdf.*asdf.*s"));
//        System.out.println(isMatch("aa","a"));
//        System.out.println(isMatch("aa","aa"));
//        System.out.println(isMatch("aaa","aa"));
//        System.out.println(isMatch("aa","a*"));
//        System.out.println(isMatch("aab","c*a*b"));
    }
}
