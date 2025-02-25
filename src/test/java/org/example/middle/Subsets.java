package org.example.middle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Given an integer array nums of unique elements, 
 * return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. 
 * Return the solution in any order.
 * 
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        var result = sub(nums, nums.length - 1, new ArrayDeque<Integer>());
        Collections.reverse(result);
        return result;
    }

    private static List<List<Integer>> sub(int[] nums, int idx, Deque<Integer> combination) {
        var result = new ArrayList<List<Integer>>();
        if (idx < 0) {
            result.add(new ArrayList<>(combination));
            return result;
        }

        combination.push(nums[idx]);

        result.addAll(sub(nums, idx - 1, combination)); // f-> [1,2,3] <-l

        combination.poll(); 

        // idx: 2
        result.addAll(sub(nums, idx - 1, combination));
        return result;
    }
}
