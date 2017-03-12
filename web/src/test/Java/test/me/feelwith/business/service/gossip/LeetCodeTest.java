package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2016/8/10.
 */
public class LeetCodeTest {
     public int[] twoSum(int[] nums, int target) {
         Map<Integer,List<Integer>> numbers = new HashMap<Integer,List<Integer>>();
         int [] arr = new int [2];
         for(int i=0;i<nums.length;i++){
             int a = nums[i];
             int b = target - a;
             List<Integer> indexs = numbers.get(a);
             if(indexs == null){
                 indexs = new LinkedList<>();
                 indexs.add(i);
                 numbers.put(a,indexs);
             }
             indexs = numbers.get(b);
             if(indexs != null){
                 if(indexs.size() == 2){
                     Integer [] tmp = new Integer [2];
                     indexs.toArray(tmp);
                     return toSortedPrimiIntArr(tmp);
                 }else{
                    if(indexs.get(0) != i){
                        arr = new int []{i,indexs.get(0)};
                        Arrays.sort(arr);
                        return arr;
                    }
                 }
             }
        }
        return arr;
    }
    private static  int [] toSortedPrimiIntArr(Integer [] ar){
        int [] arra = new int[ar.length];
        int i=0;
        while(i < ar.length){
            arra[i] = ar[i];
            i++;
        }
        Arrays.sort(arra);
        return arra;
    }
    @Test
    public void t(){
//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        int [] arr = new int []{3,2,4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(arr, target)));
    }
}
