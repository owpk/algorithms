package org.example.middle;

import org.example.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * You are given an integer array coins representing coins of different 
 * denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * 
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        return 0;
    }

    @ParameterizedTest
    @CsvSource(value = {"'[1,2,5]', 11, 3"})
    void test(String coins, int amount, int expected) {
        int[] aC = ArrayUtils.toIntArray(coins);
        var result = coinChange(aC, amount);
        Assertions.assertEquals(expected, result);
    }
}
