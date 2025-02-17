package org.example.middle;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given a string s, find the length of the longest 
 * substring without repeating characters.
 * 
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Sliding Window + HashMap, что позволит достичь O(n).
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;

        if (s.length() == 1)
            return 1;

        int result = 0, subResult = 0;

        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);

            if (map.containsKey(ch))
                subResult = Math.min(i - map.get(ch), subResult + 1);
            else subResult++;

            result = Math.max(result, subResult);
            map.put(ch, i);
        }
        return result;
    }

    /**
     * 2 Версия
     */
    public int lengthOfLongestSubstringV2(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] lastIndex = new int[128];
        
        for (int back = 0, front = 0; front < n; front++) {
            char currentChar = s.charAt(front);
            back = Math.max(back, lastIndex[currentChar]);
            maxLength = Math.max(maxLength, front - back + 1);
            lastIndex[currentChar] = front + 1;
        }
        
        return maxLength;
    }

    @ParameterizedTest
    @CsvSource({
        "'pwwkew',  3",
        "'bbbb',    1",
        "'abcabcbb', 3",
        "'au',      2",
        "'dvdf',    3",
        "'abba',    2",
        "'tmmzuxt', 5"
    })
    void test(String s, int expected) {
        var result = lengthOfLongestSubstringV2(s);
        Assertions.assertEquals(expected, result);
    }
}
