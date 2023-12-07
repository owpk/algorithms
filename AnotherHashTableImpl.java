public class AnotherHashTableImpl<K, V> extends HashTableImpl<K, V> {
    public static final int DEFAULT_CAPACITY = 16;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int initialSize;
    private int trashHold;

    @Deprecated
    public AnotherHashTableImpl(int maxSize) {
        super(maxSize);
        this.trashHold = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_LOAD_FACTOR);
    }

    public AnotherHashTableImpl(int initialSize, double loadFactor) {
        this.initialSize = initialSize <= 0 ? DEFAULT_CAPACITY : initialSize;
        this.trashHold = (int) (initialSize * loadFactor <= 0 ? DEFAULT_LOAD_FACTOR : loadFactor);
    }

    @Override
    public boolean put(K k, V v) {
        if (this.items == null || items.length == 0 ||
                size == trashHold)
            resize();
        int index = hash(k);
        Node<K, V> node = new Node<>(k, v);
        if (items[index] != null) {
            if (items[index].getValue() == v) {
                items[index].setValue(v);
            } else {
                Node<K, V> e = items[index];
                while (e.getNext() != null) {
                    e = e.getNext();
                }
                e.setNext(node);
                node.setPrev(e);
                e.depth++;
            }
        } else
            items[index] = node;
        ++this.size;
        return true;
    }

    private Node<K, V> searchInBucket(int index, K k) {
        Node<K, V> e = items[index];
        while (e != null && !e.getKey().equals(k)) {
            e = e.getNext();
        }
        return e;
    }

    @Override
    public V get(K k) {
        Node<K, V> result = searchInBucket(hash(k), k);
        return result == null ? null : result.getValue();
    }

    @Override
    public V remove(K k) {
        int index = hash(k);
        Node<K, V> node = searchInBucket(index, k);
        if (node != null) {
            Node<K, V> prev = node.getPrev();
            Node<K, V> next = node.getNext();
            if (prev == null) {
                items[index] = next;
            } else prev.setNext(next);
            if (next != null) {
                next.setPrev(prev);
            }
            --this.size;
            return node.getValue();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = items == null || items.length == 0 ? initialSize : initialSize << 1;
        Node<K, V>[] newTable = new Node[newCapacity];
        transfer(newTable);
        items = newTable;
    }

    private void transfer(Node<K, V>[] newTable) {
        for (int i = 0; i < (items != null ? items.length : 0); i++) {
            int hash = items[i] != null ? hash(items[i].getKey(), newTable.length) : i;
            newTable[hash] = items[i];
        }
    }

    @Override
    public void display() {
        Node<K, V> tableNode;
        for (int i = 0; i < items.length; i++) {
            tableNode = items[i];
            System.out.printf("[%d] %s : %s ", i,
                    tableNode == null ? null : tableNode.getKey(),
                    tableNode == null ? null : tableNode.getValue());
            if (tableNode != null && tableNode.getNext() != null) {
                Node<K, V> node = tableNode.getNext();
                while (node != null) {
                    System.out.printf(" -> %s : %s ", node.getKey(), node.getValue());
                    node = node.getNext();
                }
            }
            System.out.println();
        }
    }
}
