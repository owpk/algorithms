package org.example.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given an array of distinct integers candidates and 
 * a target integer target, return 
 * a list of all unique combinations of candidates 
 * where the chosen numbers sum to target. 
 * You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times. 
 * Two combinations are unique if the frequency of at least one of the chosen 
 * numbers is different.
 * 
 * The test cases are generated such that the number of unique combinations 
 * that sum up to target is less than 150 combinations for the given input.
 * 
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * 
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * 
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        var result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] candidates, int target, int start,
        List<Integer> currentComb, 
        List<List<Integer>> result) {

        if (target == 0) {
            result.add(new ArrayList<>(currentComb));
        }

        for (int i = start; i < candidates.length; i++) {
            var num = candidates[i];

            if (num > target)
                break; // Since candidates are sorted, no need to check further

            currentComb.add(num);
            helper(candidates, target - num, i, currentComb, result);

            currentComb.remove(currentComb.size() - 1); // Backtrack
        }
    }

    @ParameterizedTest
    @CsvSource({
            "'[2,3,6,7]', 7, '[[2,2,3],[7]]'",
            "'[2,3,5]', 8, '[[2,2,2,2],[2,3,3],[3,5]]'",
            "'[2]', 1, '[]'"
    })
    void test(String arr, int target, String expected) {
        var candidates = ArrayUtils.toIntArray(arr);
        int[][] cExpected = ArrayUtils.to2dIntArray(expected);
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + result.stream().map(it -> it.toString()).collect(Collectors.joining(",")));

        Assertions.assertEquals(cExpected.length, result.size());

        for (int i = 0; i < cExpected.length; i++) {
            Assertions.assertTrue(Arrays.equals(cExpected[i], 
                result.get(i).stream()
                    .mapToInt(Integer::intValue)
                    .toArray()));
        }
    }
}
