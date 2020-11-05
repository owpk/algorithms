package ru.geekbrains.ads.lesson2;

public class SortedArrayImpl<E extends Comparable<? super E>> extends ArrayImpl<E> {

    @SuppressWarnings("unchecked")
    public SortedArrayImpl(int initialCapacity) {
        this.data = (E[]) new Comparable[initialCapacity];
    }

    public SortedArrayImpl() {
        super();
    }

    // O(logN)
    @Override
    public int indexOf(E value) {
        int low = 0;
        int high = size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (get(mid).equals(value)) {
                return mid;
            }
            else if (value.compareTo(get(mid)) > 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // O(n)
    @Override
    public void add(E value) {
        checkAndGrow();

        int index = size();
        for (int i = 0; i < size(); i++) {
            if (get(i).compareTo(value) > 0) {
                index = i;
                break;
            }
        }

        if (index == size()) {
            data[size++] = value;
        }
        else {
            doInsert(value, index);
        }
    }

    @Override
    public void insert(E value, int index) {
        throw new UnsupportedOperationException("Invalid operation for sorted array!");
    }
}
