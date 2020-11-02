package hw;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class MyDeque<E> extends  implements Deque<E> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int MIN_GROW_THRESHOLD = 2;
    private static final int GROW_VALUE = 20;

    private int currentEmptySpace;
    private int currentMaxCapacity;

    private int head;
    private int tail;

    private Object[] elements;

    public MyDeque(E... elements) {
        this.elements = elements;
        currentEmptySpace = elements.length;
        currentMaxCapacity = elements.length;
        head = 0;
        tail = this.elements.length - 1;
    }

    public MyDeque() {
         elements = new Object[INITIAL_CAPACITY];
        currentEmptySpace = INITIAL_CAPACITY;
        currentMaxCapacity = INITIAL_CAPACITY;
        head = 0;
        tail = elements.length - 1;
    }

    private void reduceEmptySpace() {
        currentEmptySpace =- 1;
    }

    private void checkIfFull() {
        if (currentEmptySpace == MIN_GROW_THRESHOLD) {
            final Object[] elements = new Object[currentMaxCapacity + GROW_VALUE];
            System.arraycopy(this.elements, 0, elements, 0, this.elements.length);
            this.elements = elements;
            currentMaxCapacity = this.elements.length;
            currentEmptySpace = currentEmptySpace + GROW_VALUE;
        }
    }

    static int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    static int inc(int i, int distance, int modulus) {
        if ((i += distance) - modulus >= 0) i -= modulus;
        return i;
    }

    @Override
    public void addFirst(E e) {
        reduceEmptySpace();
        checkIfFull();
        elements[head = dec(head, elements.length)] = e;
    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
