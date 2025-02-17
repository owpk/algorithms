package org.example.simple;

import org.example.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * 
 * Example 3:
 * Input: head = []
 * Output: []
 * 
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        var node = new ListNode();
        var test = new ReverseLinkedList();

        for (int i = 0; i < 10; i++) {
            node.add(i);
        }

        System.out.println(node);

        var result = test.reverseList(node);
        System.out.println(result);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        
        ListNode next = head;
        ListNode res = null;

        while(next != null) {
            var tmp = next.next();
            next.next(res);
            res = next;
            next = tmp;
        }

        return res; 
    }
}
