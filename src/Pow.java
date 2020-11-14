public class Pow {
    public static void main(String[] args) {
        int result = pow(7, 6);
        System.out.println(result);
    }

    private static int pow(int base, int exponent) {
        if (exponent == 1) return base;
        else if (exponent == 0) return 1;
        return pow(base, --exponent) * base;
    }
}

