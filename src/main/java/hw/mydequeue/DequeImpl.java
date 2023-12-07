package hw.mydequeue;

import hw.myqueue.QueueImpl;

import java.util.Arrays;

public class DequeImpl<E> extends QueueImpl<E> implements Deque<E> {

    public DequeImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public E peekLast() {
        return data[tail];
    }

    @Override
    public E pollLast() {
        E el = data[tail--];
        size--;
        return el;
    }

    @Override
    public boolean addFirst(E element) {
        if (isFull()) return false;
        final Object[] tmp = Arrays.copyOf(data, size + 1);
        data = (E[]) tmp;
        head = tail + 1;
        data[head] = element;
        size++;
        return true;
    }

    @Override
    public String toString() {
        final Object[] temp = new Object[tail - head];
        System.arraycopy(data, 0, temp, 0, tail - head);
        return Arrays.toString(temp);
    }
}
