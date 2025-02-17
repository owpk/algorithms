package org.example.simple;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times. 
 * You may assume that the majority element always exists in the array.
 * 
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * 
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {

    public static void main(String[] args) {
        var result = new MajorityElement()
            .majorityElement(new int[]{1,1,1,2,3});
        System.out.println(result);
    }

    public int majorityElement(int[] nums) {
        long[] pair = new long[2]; // [count, num];
        var counts = new HashMap<Integer, Long>();

        for (int i = 0; i < nums.length; i++) {
            var lastMax = pair[0];

            var num = nums[i];
            var count = counts.compute(num, 
                (k, v) -> v == null ? 1 : v + 1);

            if (count > lastMax) {
                pair[0] = count;
                pair[1] = num;
            }
       }

       return (int) pair[1];
    }

    @ParameterizedTest
    @MethodSource("args")
    void test(int[] nums, int expected) {
        var result = majorityElement(nums);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of(new int[]{1,1,1,2,3}, 1),
            Arguments.of(new int[]{1}, 1),
            Arguments.of(new int[]{2,1,2,3,1,2,0,2}, 2)
        );
    }
}
