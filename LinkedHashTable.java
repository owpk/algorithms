public class LinkedHashTable<K, V> implements HashTable<K, V> {

    @Override
    public boolean put(K k, V v) {
        return false;
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public V remove(K k) {
        return null;
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
    public void display() {

    }
}
