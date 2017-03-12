package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 时间消耗在哪里?
 */
public class L23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if((lists == null) || (lists.length == 0)){
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if((!ListNode.class.isInstance(o1)
                || (!ListNode.class.isInstance(o2)))){
                    throw new IllegalArgumentException();
                }
                ListNode a = (ListNode)o1;
                ListNode b = (ListNode)o2;
                if(a == null){
                    return -1;
                }
                if(b == null){
                    return 1;
                }
                return a.val - b.val;
            }
        });
        for(ListNode no : lists){
            while(no != null){
                heap.add(no);
                no = no.next;
            }
        }
        ListNode result = new ListNode(0);
        ListNode head = result;
        while(heap.peek() != null){
            result.next = heap.poll();
            result = result.next;
        }
        //尼玛，应该避免ListNode因为交叉的问题,最后的一个ListNode的next会指向前面的某个ListNode而陷入死循环，比如 [[-2 ,-1,-1,-1],[]]这个就很有可能
        result.next = null;
        return head.next;
    }

    @Test
    public void te(){
        ListNode l1 = L21.build(1,2);
        ListNode l2 = L21.build();
        ListNode l3 = L21.build(3,5);
        L21.printListNode(mergeKLists(buildArr(l1)));
        Printor.println("--------------------------");
        L21.printListNode(mergeKLists(buildArr(l2)));
        Printor.println("--------------------------");
        L21.printListNode(mergeKLists(buildArr(l3)));
        Printor.println("--------------------------");
        L21.printListNode(mergeKLists(buildArr(l1,l2,l3)));
    }

    @Test
    public void e1(){
        L21.printListNode(mergeKLists(buildArr(L21.build(-2,-1,-1,-1),null)));
    }
    public ListNode[] buildArr(ListNode ... vales){
        if( (vales == null) || vales.length == 0){
            return null;
        }
        return Arrays.copyOf(vales,vales.length);
    }
}
