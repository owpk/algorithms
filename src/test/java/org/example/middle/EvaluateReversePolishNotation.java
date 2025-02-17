package org.example.middle;

import java.util.Stack;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * You are given an array of strings tokens that represents an
 * arithmetic expression in a Reverse Polish Notation.
 * 
 * Evaluate the expression. Return an integer that represents
 * the value of the expression.
 * 
 * Note that:
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish
 * notation.
 * The answer and all the intermediate calculations can
 * be represented in a 32-bit integer.
 * 
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * 
 * Example 2:
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * 
 * Example 3:
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvaluateReversePolishNotation {

    private final BiFunction<Integer, Integer, Integer>[] map = new BiFunction['/' - '*' + 1];
    {
        map['/' - '/'] = (v1, v2) -> v1 / v2;
        map['/' - '*'] = (v1, v2) -> v1 * v2;
        map['/' - '-'] = (v1, v2) -> v1 - v2;
        map['/' - '+'] = (v1, v2) -> v1 + v2;
    }

    /**
     * Инфиксное выражение
     * (1+2) × 4 + 3
     * в ОПН может быть записано так: 1 2 + 4 × 3 +
     * Вычисление производится слева направо, ввод интерпретируется как указано
     * в приведённой ниже таблице (указано состояние стека после
     * выполнения операции, вершина стека выделена красным цветом):
     * 
     * Ввод Операция Стек
     * 1 поместить в стек 1
     * 2 поместить в стек 1, 2
     * + сложение 3
     * 4 поместить в стек 3, 4
     * * умножение 12
     * 3 поместить в стек 12, 3
     * + сложение 15
     * Результат, 15, в конце вычислений находится на вершине стека.
     */
    public int evalRPN(String[] tokens) {
        var stack = new Stack<Integer>();

        for (int i = 0; i < tokens.length; i++) {
            var token = tokens[i];

            if (token.length() == 1 && token.charAt(0) < '0') {
                var operand = map['/' - token.charAt(0)];
                var v2 = stack.pop();
                var v1 = stack.pop();
                var result = operand.apply(v1, v2);
                stack.add(result);
            } else
                stack.push(parse(token));
        }

        return stack.pop();
    }

    int i;

    public int evalRPNv2(String[] tokens) {
        i = tokens.length;
        return evaluate(tokens);
    }

    private int evaluate(String[] tokens) {
        String ss = tokens[--i];
        char s = ss.charAt(0);
        int num = 0, k = 0, sign = 1;
        if (ss.length() == 1) {
            if (s < '0') {
                var operand = map['/' - s];
                var second = evaluate(tokens);
                return operand.apply(evaluate(tokens), second);
            } else
                return s - '0';
        } else if (s == '-') {
            sign = -1;
            s = ss.charAt(++k);
        }

        num = s - '0';
        k++;
        while (k < ss.length()) {
            s = ss.charAt(k++);
            num = num * 10 + s - '0';
        }

        return num * sign;
    }

    private int parse(String token) {
        return Integer.parseInt(token);
    }

    @ParameterizedTest
    @CsvSource({
            "'1 2 + 4 * 3 +', 15",
            "'4 13 5 / +', 6",
            "'10 6 9 3 + -11 * / * 17 + 5 +', 22"
    })
    void test(String tokens, int expected) {
        var split = tokens.split("\s");
        var result = evalRPNv2(split);
        Assertions.assertEquals(expected, result);
    }
}
