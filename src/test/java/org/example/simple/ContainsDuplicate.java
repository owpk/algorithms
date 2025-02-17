package org.example.simple;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given an integer array nums, return true if any value appears at 
 * least twice in the array, and return false if every element is distinct.
 * 
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: true
 * Explanation:
 * The element 1 occurs at the indices 0 and 3.
 * 
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation:
 * All elements are distinct.
 * 
 * Example 3:
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1)
            return false;

        var map = new HashMap<Integer, Long>();       

        for (int i = 0; i < nums.length; i++) {
            var count = map.compute(nums[i], (k,v) -> v == null ? 1 : v + 1);
            if (count != 1)
                return true;
        }
        return false;
    }

    @ParameterizedTest
    @MethodSource("args")
    void test(int[] nums, boolean expected) {
        var result = containsDuplicate(nums);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of(new int[]{1,2,3,4,3}, true),
            Arguments.of(new int[]{1,2,3,4,5}, false)
        );
    }
}
