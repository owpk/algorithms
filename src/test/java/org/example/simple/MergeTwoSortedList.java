package org.example.simple;

import java.util.stream.Stream;

import org.example.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * Ex 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * 
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 * 
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
class MergeTwoSortedList {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var result = new ListNode();

        if (list2 == null && list1 != null)
            return list1;
        else if (list1 == null && list2 != null) {
            return list2;
        } else if (list1 == null && list2 == null) {
            return result;
        }

        ListNode current = list1;
        ListNode second = list2;
        ListNode resultLast = null;

        // [1]    // [8]
        while (current != null) {
            if (current.compareTo(second) > 0) {
                if (second != null) {
                    resultLast = result.add(second.val());
                    second = second.next();
                } else {
                    if (resultLast.val() == null)
                        return current;
                    resultLast.next(current);
                    return result;
                }
            } else {
                resultLast = result.add(current.val());
                current = current.next();
                var tmp = current;
                current = second;
                second = tmp;
            }
        }
        return result;
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("listMerge")
    void testMerge(ListNode l1, ListNode l2, ListNode expected) {
        var result = mergeTwoLists(l1, l2);

        expected.forEachCount((it, idx) -> {
            Assertions.assertEquals(it, result.get(idx));
        });
    }

    private static Stream<Arguments> listMerge() {
        return Stream.of(
            Arguments.of(
                new ListNode(1,8), // l1
                new ListNode(1,2,3,4,5,6,7,8), // l2

                new ListNode(1,1,2,3,4,5,6,7,8,8)), // expected


            Arguments.of(
                new ListNode(1,2), 
                new ListNode(1,2,3,4,5,6,7,8),  

                new ListNode(1,1,2,2,3,4,5,6,7,8)), 


            Arguments.of(
                new ListNode(), 
                new ListNode(1,2,3,4,5,6,7,8),  

                new ListNode(1,2,3,4,5,6,7,8)),


            Arguments.of( 
                new ListNode(1,2,3,4,5,6,7,8),  
                new ListNode(),

                new ListNode(1,2,3,4,5,6,7,8)),

            Arguments.of( 
                new ListNode(1),  
                new ListNode(8),

                new ListNode(1, 8)),

            Arguments.of( 
                new ListNode(1, 9),  
                new ListNode(8),

                new ListNode(1, 8, 9)),

            Arguments.of( 
                new ListNode(10, 11),  
                new ListNode(8),

                new ListNode(8, 10, 11))
        );
    }
}
