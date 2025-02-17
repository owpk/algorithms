package org.example.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not
 * matter.
 * 
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * 
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum {
    
    public static void main(String[] args) {
        // [[-1,-1,2],[-1,0,1]]

        // -4 -1 -1 0 1 1 2 4 
        var result = new ThreeSum().threeSum(new int[] { -4, 2, -1, 1, -1, 4, 1, 0 });
        result.forEach(it -> System.out.println(it));
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     * 
     * @param nums the given array
     * @return the list of triplets
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // Сортируем массив O(n log n)
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Пропускаем одинаковые nums[i], чтобы избежать дубликатов
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // Пропускаем дубликаты в left и right
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++; // Увеличиваем left, чтобы увеличить сумму
                } else {
                    right--; // Уменьшаем right, чтобы уменьшить сумму
                }
            }
        }
        return result;
    }
}
