package test.me.feelwith.business.service.gossip;

import org.junit.Test;

/**
 * Created by L on 2017/3/6.
 */
public class L21 {

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 不同的情况下，执行的步骤是不一样的. 和递归不一样，递归除了基本情况，其他的步骤全部一样。但是这类程序依赖于每个变量的不同情况来执行
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode head = result ;
        while( (l1!= null) && (l2!= null)){
            if(l1.val < l2.val){
                result.next = l1;
                l1 = l1.next;
            }else{
                result.next = l2;
                l2 = l2.next;
            }
             result = result.next;
        }
        while(l1 != null){
            result.next = l1;
        }
        while(l2 != null){
            result.next = l2;
        }
        return head.next;
    }

    @Test
    public void t2(){
        ListNode l1 = build(2);
        ListNode l2 = build(1);
        ListNode result =  mergeTwoLists(l1,l2);
        printListNode(result);
    }

    public static void printListNode(ListNode node){
        while(node != null){
            Printor.println(node.val);
            node = node.next;
        }
    }
    public static ListNode build(int ...vals){
        if((vals == null) || vals.length==0){
            return null;
        }
        ListNode node = new ListNode(vals[0]);
        ListNode head = node;
        for(int i=1;i<vals.length;i++){
            node.next = new ListNode(vals[i]);
            node = node.next;
        }
        return head;
    }
}
