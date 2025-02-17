package org.example.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * 
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class BinaryAddition {

    public String addBinary(String a, String b) {
        int mod = 0;
        var sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
    
        // Проходим по строкам с конца
        while (i >= 0 || j >= 0 || mod == 1) {
            int sum = mod;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
    
            // Вычисляем текущий бит результата
            sb.append(sum % 2);
            // Вычисляем перенос
            mod = sum / 2;
        }
    
        return sb.reverse().toString();
    }

    private String addBinaryInternal(String a, String b) {
        var a_ = Integer.parseInt(a, 2);
        var b_ = Integer.parseInt(b, 2);
        var res = a_ + b_;
        return Integer.toBinaryString(res);
    }

    @ParameterizedTest
    @CsvSource({
        "'1010', '1011'",
        "'1010', '1101'",
        "'1110', '1111'",
        "'11', '01'",
        "'1', '100'",
        "'110', '100'",
        "'110', '110'",
        "'111', '111'",
        "'100', '111'"
    })
    void test(String a, String b) {
        var res = addBinary(a, b);
        var expected = addBinaryInternal(a, b);
        Assertions.assertEquals(expected, res);
    }
}