package grokking.coding.interviews.patterns.kwaymerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *Whenever we are given ‘K’ sorted arrays, we can use a Heap to efficiently perform a sorted traversal of all the elements of all arrays.
 * We can push the smallest (first) element of each sorted array in a Min Heap to get the overall minimum. While inserting
 * elements to the Min Heap we keep track of which array the element came from. We can, then, remove the top element from
 * the heap to get the smallest element and push the next element from the same array, to which this smallest element belonged,
 * to the heap.
 *
 * Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
 * Input: L1= [2, 6, 8], L2= [3, 6, 7], L3= [1, 3, 4]
 * Output: [1, 2, 3, 3, 4, 6, 6, 7, 8]
 *
 */

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class MergeKSortedLinkedLists {

    public static ListNode mergeKSortedLinkedLists(ListNode[] listNodes) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((a,b) -> Integer.compare(a.value, b.value));
        ListNode result = null, curr = null;

        for (ListNode listNode : listNodes) {
            if (listNode != null)
                minHeap.add(listNode);
        }

        // take the smallest (top) element form the min-heap and add it to the result;
        // if the top element has a next element add it to the heap

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            if (curr == null && result == null) {
                result = node;
                curr = node;
            }
            else {
                curr.next = node;
                curr = curr.next;
            }

            if (node.next != null)
                minHeap.add(node.next);
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = MergeKSortedLinkedLists.mergeKSortedLinkedLists(new ListNode[] { l1, l2, l3 });
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
