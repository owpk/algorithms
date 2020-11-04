package hw.mydequeue;

import hw.myqueue.QueueImpl;

public class Test {
   public static void main(String[] args) {
      Deque<String> deque = new DequeImpl<>(10);
      deque.insert("1");
      deque.insert("5");
      deque.insert("5");
      deque.insert("5");
      deque.insert("6");
      System.out.println(deque.pollLast());
      System.out.println(deque.pollLast());
      System.out.println(deque.peekHead());
   }
}
