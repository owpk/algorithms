package hw.mydequeue;

import hw.myqueue.Queue;

public interface Deque<E> extends Queue<E> {
    E peekLast();
    E pollLast();
    boolean addFirst(E element);
}
