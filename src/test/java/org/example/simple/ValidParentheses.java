package org.example.simple;

import java.util.ArrayDeque;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and
 * ']',
 * determine if the input string is valid.
 * An input string is valid if:
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * 
 * Example 1:
 * Input: s = "()"
 * Output: true
 * 
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * 
 * Example 4:
 * Input: s = "([{}]())"
 * Output: true
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s.length() % 2 != 0)
            return false;

        var closeBr = Map.of('(', ')',
        '[', ']', '{', '}'
        );

        var deq = new ArrayDeque<Character>();
        for (char it : s.toCharArray()) {
            if (deq.isEmpty()) {
                deq.add(it);
            } else {
                var close = closeBr.get(deq.getLast());
                if (close != null && it == close)
                    deq.pollLast();
                else deq.add(it);
            }
        }

        return deq.isEmpty();
    }

    @ParameterizedTest(name = "{index} -> {0} : {1}")
    @CsvSource({"([{}]()), true", "[}, false", "(({)), false", "()[], true"})
    void test(String s, boolean result) {
        Assertions.assertEquals(result, isValid(s));
    }
}
