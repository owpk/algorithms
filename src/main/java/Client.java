import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

public class Client {
    public static void main(String[] args) {
        String someDate = "Hello World!!";
        String reversed = process(reverseString, someDate);

        System.out.println(reversed);
        Deque<String> d = new ArrayDeque<>(3);
        System.out.println(d.size());
        d.add("1");
        d.addLast("2");
        d.addFirst("3");
        d.addFirst("3");
        d.addFirst("3");
        System.out.println(d + ":" + d.size());
    }

    private static final Function<String, String> reverseString = x -> x.chars()
            .mapToObj(c -> (char)c)
            .reduce("", (s,c) -> c+s, (s1,s2) -> s2+s1);

    private static <T,R> R process(Function<T, R> function, T input) {
        return function.apply(input);
    }
}
