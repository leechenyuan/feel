package test.me.feelwith.business.service.gossip;

import org.junit.Test;

/**
 * 耗时长达3个小时！
 * 耗时的地方：
 *  边界考虑不到位
 *      首先，必须要考虑1个元素、2个元素、3个元素的情况，每个元素对应的这个跟踪引用的值得范围必须处理好！
 *      刚开始没有认真从1-3个元素开始考虑，脑袋离散得考虑了4个元素的情况和1个元素的情况，导致认识很不全面，边界处理不好，经常报空指针，并且
 *      由于只考虑了4个元素和1个元素的情况，我以为只用2个跟踪引用我就可以搞定，后来理性地从1、2、3个元素开始考虑后，才慢慢得出结果
 *  变量不足。
 *      本来以为只用两个标量标记就行，后来发现我必须用到3个变量跟踪指针才行
 *  教训:
 *      边界处理很不到位！边界处理不到位的情况，是由于脑袋不会理性思考。比如：用枚举法的时候，我们应该从最基本的情况(0,1,2,3个元素)开始考虑，
 *      后面的情况基本都是基本情况的扩展，只要基本情况对，后面的都对。 考虑到基本情况以后还是不够，你必须对于每种基本情况，比如0、1个元素，每个
 *      跟踪引用的初始化、取值、取值范围边界，，，这些情况都考虑到位后，逻辑就能够理清了，自然就能够写出程序来.
 *
 */
public class L19 {
 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return head;
        }
        if(n == 0){ //这句好像可以不加
            return head;
        }
        ListNode pre = null;
        ListNode target = head;
        ListNode tail = head;
        for(int i=0;i<n;i++){
            tail = tail.next;
        }
        while(tail !=null){
            pre = target;
            target = target.next;
            tail = tail.next;
        }
        if(pre == null){
            if(target.next == null){
                head = null;
            }else{
                head = target.next;
            }
        }else{
            pre.next = target.next;
        }
        return head;
    }

    @Test
    public void t(){
        int [] nums = new int []{
//                1
//                1,2
//                1,2,3
        };
        int n =
                1
//                2
//                3
                ;
        ListNode head = buildList(nums);
        head = removeNthFromEnd(head,n);
        while(head != null ){
            System.out.println(head.val);
            head = head.next;
        }
    }

    ListNode buildList(int [] nums){
        ListNode head = new ListNode(nums[0]);
        ListNode node = head;
        for(int i=1;i<nums.length;i++){
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return head;
    }
}
