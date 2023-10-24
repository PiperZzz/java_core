package base.core.leetcode.two_pointers;

import base.core.leetcode.ListNode;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Boundary Condition Check: if the input lists are null
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
    
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
    
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }
    
        return dummy.next;
    }

    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        // Boundary Condition Check: if the input lists are null
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1; 
        } else {
            int val1 = l1.value;
            int val2 = l2.value;
            if (val1 < val2) {
                l1.next = mergeTwoListsRecursive(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoListsRecursive(l1, l2.next);
                return l2;
            }
        }
    }
}
