package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by L on 2017/2/28.
 */
public class L22_02 {
    public List<String> generateParenthesis(int n) {
        return  doIt(n);
    }

    /**
     * ()
     *
     * (())-()()
     *
     * ((()))- (()())- (())()- ()(())- ()()()
     *
     * 从第一个到第三个，我们可以看到组成规律 : 上下、左右 ,上下代表逐层嵌套,左右代表分成左右两部分
     * f(n) = "(" + f(n-1) + ")"
     *        +  ( "("+f(n-2)+")" + f(1))
     *        ....
     *        + (f(1)+f(n-1)
     * 和斐波那契不同的是,在这里f(n)返回的是集合,需要做集合运算
     * @param n
     * @return
     */
    public List<String> doIt(int n){
        if(n == 0){
            return Arrays.asList("");
        }
        if(n == 1){
            return Arrays.asList("()");
        }
        List<String> result = new ArrayList<>(n);
        List<String> p1 = doIt(n-1);
        for(String s:p1){
            result.add("("+s+")");
        }
        for(int i=n-1;i>0;i--){
            List<String> right = doIt(i-1);
            List<String > left = doIt(n-i);
            for(String r : right){
                for(String l : left){
                    result.add("("+r+")"+l);
                }
            }
        }
        return result;
    }

    /**
     * 咦，好像可以写成类似斐波那契那样的样子，通过缓存中间结果来加速，避免直接递归
     * @param n
     * @return
     */
//    public List<String> doItV2(int n){
//        List<String> a = Arrays.asList("");
//        List<String> b = Arrays.asList("()");
//        if(n == 0){
//            return a;
//        }
//        if(n == 1){
//            return b;
//        }
//        int i= 0;
//        while((++i)<n) {
//            List<String> p1 = new ArrayList<>(b.size());
//            for(String s:b){
//                p1.add("(" + s + ")");
//            }
//            List<String> p2 = new ArrayList<>();
//            for(String s : a){
//                for(String r : b){
//
//                }
//            }
//        }
//    }
    @Test
    public void te(){
        int n = 0;
        List<String> li = generateParenthesis(n);
        Printor.println(li);
        Printor.println(li.size());
    }
}
