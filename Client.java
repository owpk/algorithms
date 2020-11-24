import java.util.Objects;

public class Client {
    public static void main(String[] args) {
        HashTable<BadEntity, String> hashTable = new HashTableImpl<>(5);
        hashTable.put(new BadEntity("Klone1"), "Sasha");
        hashTable.put(new BadEntity("Klone2"), "Petya");
        hashTable.put(new BadEntity("Klone3"), "Kolya");
        hashTable.put(new BadEntity("Klone4"), "Dima");
        hashTable.put(new BadEntity("Klone5"), "Vitya");
    }

    private static class BadEntity {
        private final String localKeyToHash;

        public BadEntity(String localKeyToHash) {
            this.localKeyToHash = localKeyToHash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BadEntity badEntity = (BadEntity) o;
            return Objects.equals(localKeyToHash, badEntity.localKeyToHash);
        }

        @Override
        public int hashCode() {
            return Objects.hash(localKeyToHash);
        }
    }

}
