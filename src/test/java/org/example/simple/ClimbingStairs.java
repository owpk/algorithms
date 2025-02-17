package org.example.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        var result = new ClimbingStairs().climbStairs(44);
        System.out.println(result);
    }

    public int climbStairs(int n) {
        return fibonacci(n + 1, new HashMap<>());
    }

    private int fibonacci(int i, Map<Integer, Integer> cache) {
        return Optional.ofNullable(cache.get(i))
            .orElseGet(() -> {
            if (i == 1) {
                return 1;
            } 
            if (i <= 0) {
                return 0;
            }
            var sum = fibonacci(i - 1, cache) + fibonacci(i - 2, cache);
            cache.put(i, sum);
            return sum;
        });
    }

}
