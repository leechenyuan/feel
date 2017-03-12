package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/25.
 */
public class L16 {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null){
            return 0;
        }
        Arrays.sort(nums);
        Integer result = null;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<nums.length-2;i++){
            int sum = target - nums[i];
            int lo = i+1;
            int hi = nums.length - 1;
            while(lo<hi) {
                int s = nums[lo] + nums[hi];
                int tripleSum = nums[i]+nums[lo]+nums[hi];
                int abs = Math.abs(tripleSum-target);
                if(abs<min){
                    min = abs;
                    result = tripleSum;
                }
                if(s>sum){
                    hi--;
                }else if(s<sum){
                    lo++;
                }else{
                    return target;
                }
            }
        }
        return result;
    }

    @Test
    public void t(){
        int target = 1;
        int [] nums = new int [] {
//            1,2,2,-2,3,-5
//                -1,2,1,-4
                0,5,5,5,2,3,1,-1
        };
        Printor.println(threeSumClosest(nums,target));
    }
}
