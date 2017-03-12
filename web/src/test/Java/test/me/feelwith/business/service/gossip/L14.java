package test.me.feelwith.business.service.gossip;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/21.
 */
public class L14 {
//    public String longestCommonPrefix(String[] strs) {
//        if(strs == null ||
//                strs.length == 0){
//            return "";
//        }
//        int minLeng = Integer.MAX_VALUE;
//        for(int i=0;i<strs.length;i++){
//            if(strs[i]==null ){
//                return "";
//            }
//            if(strs[i].length()<minLeng){
//                minLeng = strs[i].length();
//            }
//        }
//
//        boolean isSame = true;
//        for(int i=0;i<minLeng;i++){
//            char c = strs[0].charAt(i);
//            for(int j=1;j<strs.length;j++){
//                if(strs[j].charAt(i) != c){
//                    if(i==0){
//                        return "";
//                    }else{
//                        return strs[j].substring(0,i);
//                    }
//                }
//            }
//        }
//        return strs[0].substring(0,minLeng);
//    }

    public String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0)    return "";
    String pre = strs[0];
    int i = 1;
    while(i < strs.length){
        while(strs[i].indexOf(pre) != 0)
            pre = pre.substring(0,pre.length()-1);
        i++;
    }
    return pre;
}
   @Test
    public void t(){
       Assert.assertEquals(-1,"abc".indexOf(""));
       String [] strs = new String[]{
//               "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
          "abxzz"
               ,""
               ,"abxzd"
       } ;
       Assert.assertEquals("a",longestCommonPrefix(strs));

    }
}
