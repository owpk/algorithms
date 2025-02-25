package org.example.middle;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Given an array nums of distinct integers, return all the possible 
 * permutations. You can return the answer in any order.
 * 
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * 
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        var result = new ArrayList<List<Integer>>();
        helper(nums, new int[nums.length], result, new ArrayList<>());
        return result;
    }

    private void helper(int[] nums, int[] used,
        List<List<Integer>> result, 
        List<Integer> comb) {

        if (comb.size() == nums.length) {
            result.add(new ArrayList<>(comb));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            var num = nums[i];

            if (used[i] == 1)
                continue;

            comb.add(num);
            used[i] = 1;

            helper(nums, used, result, comb);

            comb.remove(comb.size() - 1);
            used[i] = 0;
        }
    }

    @Test
    void test() {
        var result = permute(new int[]{1, 2, 3});
        System.out.println(result);
    }
}
