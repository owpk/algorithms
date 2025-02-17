package org.example.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given a string s which consists of lowercase or uppercase letters, return the
 * length of the longest
 * palindrome that can be built with those letters.
 * 
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 * 
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose
 * length is 7.
 * 
 * Example 2:
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is
 * 1.
 */
public class LongestPalindrom {

    public int longestPalindrome(String s) {
        if (s == null)
            return 0;
        else if (s.length() == 1 || s.isBlank())
            return 1;

        var counters = new int['z' + 1 - 'A'];

        int[] result = new int[2];

        for (int i = 0; i < s.length(); i++) {
            var current = s.charAt(i);
            var count = ++counters['z' - current];
            counters['z' - current] = count;
        }

        for (int i = 0; i < counters.length; i++) {
            var count = counters[i];
            if (count % 2 == 0) {
                result[0] = result[0] + count;
            } else {
                result[0] = result[0] + count - 1;
                result[1] = 1;
            }
        }

        if (result[0] == 0)       
            return 0;
        return result[0] + result[1];
    }

    @ParameterizedTest
    @CsvSource({
            "'aabcdefghijklmnpo', 3",
            "'abcd',    0",
            "'a',       1", // a | 1 | 1
            "'aa',      2", // aa | 2 | 2
            "'aab',     3", // aba | 2 + 1 | 3     
            "'aaab',    3", // aba | aaa | 3 + 1 | 3 | (2 + 1) + (2 + 1) + 1
            "'aaabb',   5", // baaab | 3 + 2 | 5
            "'aaacbbb', 5", // abababa | 3 + 1 + 3 | (2 + 1) + 1 + (2 + 1) | 4 + 1 | 5
            "'aaacbb',  5", // ababa | bacab | 3 + 1 + 2 | (2 + 1) + 1 + 2 |
            "'aaabcd',  3", // aba | aaa | aca | (1 + 1 + 1) + 3 | (2 + 1) + 1 
            "'aaabbbcccddde', 9", // abcd (abcde)  dcba | 3 + 3 + 3 + 3 + 1 | (2 + 1) + (2 + 1) + (2 + 1) + (2 + 1) + 1
            "'aaaabcd', 5", // aa(b/c/d)aa .. | 1 + 1 + 1 + 4 | 4 + (1) = (2 + 1 + 2)
            "'aabb',    4", 
            "'ab',      0",
            "'Aa',      0",
            "'abccccdd', 7",
            "'aaaabbbbcc', 10", // aabbccbbaa | aabbccbbaa | 4 + 2 + 4
            "'aaaabbbbccddef', 13", // aabbcd(f)dcbbaa | aabbcd(e)dcbbaa | 4 + 4 + 2 + 2 + (1 + 1) | 4 + 2 + 1 + 2 +
                                    // 4 | 2 + 2 + 2 + 1 + 2 + 2 + 2
            "'aaaabbbbcccddef', 13" // aabbcd(e)dcbbaa | aabbcd(c)dcbbaa | 4 + 4 + 3 + 2 + (1 + 1) | 4 + 3 + 4 | 2 +
                                    // 2 + 2 + 1 + 2 + 2 + (1) + (1)
    // 5 + 4 + 3 + (n) + 3 + 4 + 5 | 5 + 5 + 4 + 4 + 3 + 3 + n
    // 4 + 3 + 4
    // 3 + 3 + 3 | aaabbbccc | abc (abc) cba
    // 4 + 3 + 4 | aaaabbbcccc | abca b (b) b acba
    // 4 + 4 + 4 | aaaabbbbcccc | abca bb () bb acba
    // 2 + 2 + 4 + 5 + 6 + 9 + 1 + 1| (2) + (2) + (2) + (2 + 2) + (2 + 2 + 1) + (2 +
    // 2 + 2 + 2) + (2 + 2 + 2 + 2 + 1) + (2 + 2 + 2 + 2 + 1) + 1
    // 5 + 4 | 4 + 4 + 1 | 4 + 1 + 4
    // 3 + 4 | aaabbbb | abbabba | 2 + 2 + 3
    })
    void test(String s, int expected) {
        var result = longestPalindrome(s);
        Assertions.assertEquals(expected, result);
    }
}
