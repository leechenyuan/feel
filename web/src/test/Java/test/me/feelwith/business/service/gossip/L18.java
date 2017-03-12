package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 */
public class L18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>>  result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3 ;i++){
            if(!( i==0 ||
                    (nums[i]!=nums[i-1]))){
                continue;
            }
            int a = nums[i];
            for(int j=i+1;j<nums.length-2;j++){
                if(!((j== i+1) ||
                        (nums[j]!=nums[j-1]))){
                     continue;
                }
                int b = nums[j];
                int cdSum = target - a - b;
                int lo = j+1;
                int hi = nums.length - 1;
                while(lo<hi){
                    int c = nums[lo];
                    int d = nums [hi];
                    int cd = c+d;
                    if(cd == cdSum){
                        result.add(Arrays.asList(a,b,c,d)) ;
                        while((lo <hi) && (nums[lo] == c)) lo++; //while((lo <hi) && (nums[lo] == c)) lo++ 为什么不等于 while((lo <hi) && (nums[lo++] == c))????
                        while((lo < hi) &&  (nums[hi] == d)) hi--;
                    }else if(cd < cdSum){
                        lo ++ ;
                    }else{
                        hi --;
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void t(){
        L16 l = new L16();
        int target = -1;
        int [] nums = new int [] {
//            1,2,2,-2,3,-5
//                -1,2,1,-4
//                0,5,5,5,2,3,1,-1
//          1, 0, -1, 0, -2, 2
//                -3,-2,-1,0,0,1,2,3
                -1,0,1,2,-1,-4
        } ;
        Printor.println(fourSum(nums,target));
    }
}
