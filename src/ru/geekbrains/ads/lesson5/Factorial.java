package ru.geekbrains.ads.lesson5;

//5! = 5 * 4 * 3 * 2 * 1 = 120
public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(tailFact(5));
    }

    private static int tailFact(int n) {
        return fact(1, n);

    }

    private static int fact(int acc, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be 0 or positive value!");
        }

        if (n == 0 || n == 1) {
            return acc;
        }

        return fact(acc * n, n - 1);
    }

    private static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be 0 or positive value!");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
