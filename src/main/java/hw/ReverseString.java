package hw;

import hw.mystack.Stack;
import hw.mystack.StackImpl;

import java.util.function.Function;

public class ReverseString {

   //Задание 2:
   public static void main(String[] args) {

      //1 Подход.
      String someDate = "Hello World!!";
      String reversed = process(reverseString, someDate);

      System.out.println(reversed);

      //2 Подход.
      Stack<Character> stack = new StackImpl<>(boxToObject(someDate));
      while (!stack.isEmpty()) {
         System.out.print(stack.pop());
      }
   }

   //1.
   private static final Function<String, String> reverseString = x -> x.chars()
          .mapToObj(c -> (char) c)
          .reduce("", (s, c) -> c + s, (s1, s2) -> s2 + s1);

   private static <T, R> R process(Function<T, R> function, T input) {
      return function.apply(input);
   }

   //2.
   private static Character[] boxToObject(String data) {
      return data.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
   }

}
