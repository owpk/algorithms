package org.example.simple;

import java.util.LinkedHashSet;
import java.util.Set;

public class Fibonacci {

    public static void main(String[] args) {
        var result = new Fibonacci().fibonacci(8);
        System.out.println(result);
    }

    Set<Integer> fibonacci(int i) {
        var res = new LinkedHashSet<Integer>();
        fibonacci(i, res);
        return res;
    }

    private int fibonacci(int i, Set<Integer> result) {
        if (i == 1) {
            return 1;
        } 
        if (i <= 0) {
            return 0;
        }
        var sum = fibonacci(i - 1, result) + fibonacci(i - 2, result);
        result.add(sum);
        return sum;
    }
}