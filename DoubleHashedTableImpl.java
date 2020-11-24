public class DoubleHashedTableImpl<K, V> extends HashTableImpl<K, V> {
    private static final int DOUBLE_HASH_CONST = 5; // Должно быть простым числом

    public DoubleHashedTableImpl(int maxSize) {
        super(maxSize);
    }

    private int doubleHash(K k) {
        return DOUBLE_HASH_CONST - (k.hashCode() % DOUBLE_HASH_CONST);
    }

    @Override
    protected int getProbe(K k) {
        return doubleHash(k);
    }
}
