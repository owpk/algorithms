package org.example.simple;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        return size(nums, 0, nums.length, target);
    }

    private int size(int[] nums, int left, int right, int target) {
        if (left <= right) {
            var mid = left + (right - left) / 2;

            if (mid >= nums.length || mid < 0)
                return -1;

            var num = nums[mid];

            if (num == target)
                return mid;
            else if (num > target)
                return size(nums, left, mid - 1, target);
            else return size(nums, mid + 1, right, target);
        }
        return -1;
    }

    @ParameterizedTest
    @MethodSource("args")
    public void test(int[] nums, int target, int expected) {
        var result = search(nums, target);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[] { 5 }, 5, 0),
                Arguments.of(new int[] { -1, 0, 3, 5, 9, 12 }, 2, -1),
                Arguments.of(new int[] { -1, 0, 3, 5, 9, 12 }, 13, -1),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 5, 4),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 6, 5),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 1, 0),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 3, 2),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 2, 1),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 0, -1),
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 4, 3));
    }
}
