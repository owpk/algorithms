package org.example.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given two strings ransomNote and magazine, return true if 
 * ransomNote can be constructed by using the letters from magazine and false otherwise.
 * 
 * Each letter in magazine can only be used once in ransomNote.
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * 
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * 
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * 
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null || ransomNote == null || magazine.length() == 0)
            return false;

        int[] arr = new int['z' + 1 - 'a'];

        for (int i = 0; i < magazine.length(); i++)
            arr['z' - magazine.charAt(i)]++;

        for (int i = 0; i < ransomNote.length(); i++) {
            var target = 'z' - ransomNote.charAt(i);
            var count = arr[target];
            arr[target] = --count;
            if (count < 0)
                return false;
        }
        return true;   
    }

    @ParameterizedTest
    @CsvSource({
        "'aa', 'ab', false",
        "'aa', 'aab', true", 
        "'a', 'b', false"
    })
    void test(String note, String magazie, boolean expected) {
        var result = canConstruct(note, magazie);
        Assertions.assertEquals(expected, result);
    }
}
