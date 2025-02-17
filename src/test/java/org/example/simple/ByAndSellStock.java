package org.example.simple;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing 
 * a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * 
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * 
 * Example 2:
 * Input: prices = [7,6,4,3,1,100]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.

 */
public class ByAndSellStock {

    public int maxProfit(int[] prices) {
        var maxProfit = 0;
        int buy = 0;
        for (int sell = 1; sell < prices.length; sell++) {
            if (prices[buy] > prices[sell]) {
                buy = sell;
                continue;
            }
            maxProfit = Math.max(maxProfit, prices[sell] - prices[buy]);
        }

        return maxProfit;
    }

    @ParameterizedTest
    @MethodSource("args")
    void testMaxProfit(int[] prices, int expected) {
        var result = maxProfit(prices);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of(new int[]{7,1,5,3,6,4}, 5),
            Arguments.of(new int[]{7,5,3,1}, 0),
            Arguments.of(new int[]{1,2}, 1),
            Arguments.of(new int[]{}, 0)
        );
    }
}
