package hw.mystack;

import java.util.Arrays;

public class StackImpl<E> implements Stack<E> {

    private E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public StackImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
    }

    public StackImpl(E[] data) {
        this.data = data;
        size = data.length;
    }

    @Override // O(1)
    public void push(E value) {
        data[size++] = value;
    }

    @Override // O(1)
    public E pop() {
        return data[--size];
    }

    @Override // O(1)
    public E peek() {
        return data[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);

    }
}
