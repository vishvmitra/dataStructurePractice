package grokking.coding.interviews.patterns.slowandfast;

import java.util.Arrays;
import java.util.List;

public class StartOfCycle {

    public static ListNode findStartOfCycle(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        slow = head.next;
        fast = head.next;

        if (fast != null) {
            fast = fast.next;
        }

        while (fast != null && fast != slow) {
            if (fast == slow) {
                break;
            }

            slow = slow.next;

            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }

        if (fast == null) {
            return null;
        }

        fast = head;

        while (fast != null &&  slow != null && fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + findStartOfCycle(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + findStartOfCycle(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + findStartOfCycle(head).value);
    }
}
