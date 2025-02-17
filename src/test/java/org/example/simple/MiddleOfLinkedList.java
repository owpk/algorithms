package org.example.simple;

import java.util.stream.Stream;

import org.example.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given the head of a singly linked list, return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 * 
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * 
 * Example 2:
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 */
public class MiddleOfLinkedList {

    public ListNode middleNode(ListNode head) {
        var slow = head;
        var fast = head;

        // 1 -> 2 -> 3 -> 4
        while (fast != null) {
            if (fast.next() != null)
                fast = fast.next().next();
            else return slow;
            slow = slow.next();
        }

        return slow;
    }

    @ParameterizedTest
    @MethodSource("args")
    void test(ListNode node, int expected) {
        var result = middleNode(node);
        Assertions.assertEquals(expected, result.val());
    }

    private static Stream<Arguments> args() {
        var list1 = createList(4);
        var list2 = createList(3);
        var list3 = createList(2);
        var list4 = createList(1);

        return Stream.of(
            Arguments.of(list1, 3),
            Arguments.of(list2, 2),
            Arguments.of(list3, 2),
            Arguments.of(list4, 1)
        );
    }

    private static ListNode createList(int size) {
        var list1 = new ListNode();
        for (int i = 0; i < size; i++)
            list1.add(i + 1);
        return list1;
    }

}
