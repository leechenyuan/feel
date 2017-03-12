package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2016/9/25.
 */
public class L15 {
    public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>();
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    /*
                    比如 a = num[i]、b == num[lo],lo = i+1, c = nums[hi],hi = num.length-1;
                    当lo++后,nums[lo] == nums[lo-1],我们的目的是拿到不等于当初num[lo]的那个数,所以while循环后，比如 lo++
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    lo++;
                    等效于
                    int b = nums[lo];
                    while(b == nums[lo++]);
                    循环过后，nums[lo] > b 了
                     */
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    while (lo < hi && num[hi] == num[hi-1]) hi--;
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
           }
        }
    }
    return res;
}

//     public List<List<Integer>> threeSum(int[] nums) {
//         List<List<Integer>> result = new ArrayList<>();
//        if(nums == null || nums.length == 0){
//            return result;
//        }
//         Map<Integer,List<Integer>> indexeMap = new HashMap<>((int)(nums.length*1.33));
//         for(int i=0;i<nums.length;i++){
//             int number = nums[i];
//             List<Integer> indexList = indexeMap.get(number);
//             if(indexList == null){
//                 indexList = new ArrayList<>();
//                 indexeMap.put(number,indexList);
//             }
//             indexList.add(i);
//         }
//         Set<Integer> keySet = indexeMap.keySet();
//         int [] keys = new int [keySet.size()];
//         int r=0;
//         for(Integer k : keySet){
//            keys[r++]  = k;
//         }
//         Arrays.sort(keys);//排序
//
//         for(int i=0;i<keys.length;i++){
//            int a = keys[i];
//            for(int j=i;j<keys.length;j++){
//                int b = keys[j];
//                 if(b == a){
//                    if(indexeMap.get(b).size() < 2)   {
//                        continue;
//                    }
//                 }
//                 int c = 0-a-b;
//                 if(c < b){
//                     continue;
//                 }
//                 List<Integer> cIndexes = indexeMap.get(c);
//                 if(cIndexes == null){
//                     continue;
//                 }
//                 if(c == a){
//                     if(cIndexes.size() < 3){
//                         continue;
//                     }
//                 }else if(c== b){
//                     if(cIndexes.size() < 2){
//                         continue;
//                     }
//                 }
//                 result.add( buildTriple(a,b,c));
//             }
//         }
//         return  result;
//    }
//
//    private static List<Integer> buildTriple(int a,int b,int c){
//        List<Integer> trple = new ArrayList<>(3);
//        trple.add(a);
//        trple.add(b);
//        trple.add(c);
//        return trple;
//    }
//
//

   @Test
   public void t(){
       int [] nums =
//               new int []{1,3,4}
//               new int []{-1, 0, 1, 2, -1, -4}
//       new int []{-1, 0, 0, 2, -1, -4}
//       new int []{-1, 0, 0, 2, -1, 0,-4}
//        new int [] {1,2,-2,-1}
       new int [] {
//               -1,0,0,0,2
               2,2,2,2,2,0,0,0,0,-2,-2,-2,-2
       }
               ;
//       new int [] {-1,0,0,0,2}
//               ;
       Printor.println(threeSum(nums));
   }
}
