public interface HashTable<K, V> {
    boolean put(K k, V v);
    V get(K k);
    V remove(K k);
    int size();
    boolean isEmpty();
    void display();
    interface  Entry<K, V> {
        K getKey();
        V getValue();
        void setValue(V v);
    }
}
