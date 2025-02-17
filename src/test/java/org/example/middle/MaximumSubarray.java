package org.example.middle;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given an integer array nums, find the subarray
 * with the largest sum, and return its sum.
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * 
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * 
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * 
 * TIPS: Use Kadan's Algorithm
 */
public class MaximumSubarray {

    //             6         25             10             101
    //           |  |      |   |       |         |       |    |
    // [-10, -1, 1, 5, -8, 5, 20, -40, 1, 5, -1, 5, -10, 1, 100]
    public int maxSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;

        var stepSum = nums[0];
        var result = stepSum;

        int fw = 1;

        // -1 1 5
        while (fw < nums.length) {
            var v2 = nums[fw];
            stepSum = Math.max(v2, stepSum + v2);
            result = Math.max(result, stepSum);
            fw++;
        }
        return result;    
    }

    private int bruteForce(int[] nums) {
        var result = nums[0];

        for (int i = 0; i < nums.length; i++) {
            var sum = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                result = Math.max(sum, result);
            }

            result = Math.max(sum, result);
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("args")
    void test(int[] nums, int expected) {
        var result = maxSubArray(nums);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("args")
    void testBruteForce(int[] nums, int expected) {
        var result = bruteForce(nums);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of(new int[]{-1, -10}, -1),
            Arguments.of(new int[]{-10, -1}, -1),
            Arguments.of(new int[]{-10, -1, 1, 5, -8, 5, 20, -40, 1, 5, -1, 5, -10, 1, 100}, 101),
            Arguments.of(new int[]{-10, -1, 1, 5, -8}, 6),
            Arguments.of(new int[]{-10, 1, 1, -8, 40}, 40)
        );
    }
    
}
