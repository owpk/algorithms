package org.example.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into 
 * lowercase letters and removing all non-alphanumeric characters, it reads the 
 * same forward and backward. Alphanumeric characters include letters and numbers.

 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * 
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * 
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 */
public class ValidPalindrome {
    private static int numLow = 48;
    private static int numHigh = 57;

    private static int low = 97;
    private static int hight = 122;

    public boolean isPalindrome(String s) {
        if (s == null)
            return false;
        else if (s.length() == 1)
            return true;

        var chars = s.toCharArray();

        for (int l = 0, r = s.length() - 1; l < r;) {
            var charLeft = Character.toLowerCase(chars[l]);
            var charRight = Character.toLowerCase(chars[r]);
            var leftIsValid = isValid(charLeft);
            var rightIsValid = isValid(charRight);
            
            if (leftIsValid && rightIsValid) {
                if (charLeft != charRight)
                    return false;
                else 
                    l++; r--;
            } else if (leftIsValid && !rightIsValid) {
                r--;
            } else if (rightIsValid && !leftIsValid) {
                l++;
            } else {
                l++; r--;
            }
        }

        return true;
    }

    private boolean isValid(char c) {
        return (c >= low && c <= hight) || (c >= numLow && c <= numHigh);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "0P | false",
        "' ' | true",
        "., | true",
        "'' | true",
        "A man, a plan, a canal: Panama | true",
        "race a car | false",
        "aa | true",
        "aaa | true",
        "aba | true"
    })
    void test(String s, boolean expected) {
        var result = isPalindrome(s);
        Assertions.assertEquals(expected, result);
    }
}
