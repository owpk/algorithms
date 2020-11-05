package ru.geekbrains.ads.lesson4;

public class Test {
   public static void main(String[] args) {
      LinkedList<String> linkedList = new SimpleLinkedListImpl<>();
      linkedList.insertFirst("val");
      linkedList.insertFirst("val1");
      linkedList.insertFirst("val2");

      linkedList.forEach(x -> System.out.println(
             x.chars().mapToObj(i -> (char) i).reduce("", (s1,s2) -> s2 + s1, (s3,s4) -> s4 + "" + s3)));

      for (String s: linkedList) {
         System.out.println(s);
      }
   }
}
