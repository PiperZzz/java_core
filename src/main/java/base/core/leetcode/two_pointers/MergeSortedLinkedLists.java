package base.core.leetcode.two_pointers;

import base.core.leetcode.ListNode;

public class MergeSortedLinkedLists {
    public ListNode mergeByLoop(ListNode listNode1, ListNode listNode2) {
        // Boundary Condition Check
        if (listNode1 == null && listNode2 == null) {
            return null;
        }
        
        // Initialization 
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
    
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.value < listNode2.value) {
                current.next = listNode1;
                listNode1 = listNode1.next;
            } else {
                current.next = listNode2;
                listNode2 = listNode2.next;
            }
            current = current.next;
        }
    
        if (listNode1 != null) {
            current.next = listNode1;
        } else {
            current.next = listNode2;
        }
    
        return dummy.next;
    }

    public static ListNode mergeByRecursion(ListNode listNode1, ListNode listNode2) {
        // Boundary Condition Check: if the input lists are null
        if (listNode1 == null && listNode2 == null) {
            return null;
        }

        if (listNode1 == null) {
            return listNode2;
        } else if (listNode2 == null) {
            return listNode1; 
        } else {
            int value1 = listNode1.value;
            int value2 = listNode2.value;
            if (value1 < value2) {
                listNode1.next = mergeByRecursion(listNode1.next, listNode2);
                return listNode1;
            } else {
                listNode2.next = mergeByRecursion(listNode1, listNode2.next);
                return listNode2;
            }
        }
    }
}
