import java.util.Arrays;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    static class Node<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Node(K key, V val) {
            this.key = key;
            this.value = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " : " + value;
        }
    }

    private final Node<K, V>[] items;
    private int size;

    protected int getProbe(K k) {
        return 1;
    }

    @SuppressWarnings("unchecked")
    public HashTableImpl(int maxSize) {
        this.items = new Node[maxSize * 2];
    }

    @Override
    public boolean put(K k, V v) {
        int index = hash(k);

        if (size == items.length) {
            return false;
        }

        while (items[index] != null) {
            if (items[index].key.equals(k)) {
                items[index].setValue(v);
                return true;
            }
            index = index + getProbe(k);
            index %= items.length;
        }
        items[index] = new Node<>(k, v);
        size++;
        return false;
    }

    private int hash(K k) {
        return k.hashCode() % items.length;
    }

    @Override
    public V get(K k) {
        int index = indexOf(k);
        return index == -1 ? null : items[index].getValue();
    }

    private int indexOf(K k) {
        int index = hash(k);
        while (items[index] != null) {
            Node<K, V> current = items[index];
            if (current.getKey().equals(k)) {
                return index;
            }
            index = index + getProbe(k);
            index %= items.length;
        }
        return -1;
    }

    @Override
    public V remove(K k) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(Arrays.toString(items));
    }
}
