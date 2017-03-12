package test.me.feelwith.business.service.gossip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */
public class L22 {
    private int length ;
    private char _1 = '(';
    private char _0 = ')';
    public List<String> generateParenthesis(int n) {
        this.length = n;
        List<String> result = new ArrayList<>();
       for(int i=1;i<n;i++){

        }
        return result;
    }
    List<String> getPa(int num){
        int pre = length - num;
        return Collections.emptyList();
    }

    private String repeat(char c,int n){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<n;i++){
            builder.append(c);
        }
        return builder.toString();
    }
}
