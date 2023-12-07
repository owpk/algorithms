import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        HashTable<TestBadHashFuncClass, String> table =
                new AnotherHashTableImpl<>(4, AnotherHashTableImpl.DEFAULT_LOAD_FACTOR);
        table.put(new TestBadHashFuncClass("AB"), "Val1");
        table.put(new TestBadHashFuncClass("AC"), "Val2");
        table.display();
        System.out.println("--------");

        table.put(new TestBadHashFuncClass("BB"), "Val3");
        table.put(new TestBadHashFuncClass("BC"), "Val4");
        table.put(new TestBadHashFuncClass("BD"), "Val5");
        table.put(new TestBadHashFuncClass("C"), "Val6");
        table.display();
        System.out.println("--------");

        table.remove(new TestBadHashFuncClass("BC"));
        table.display();
        System.out.println("--------");

        table.remove(new TestBadHashFuncClass("BB"));
        table.display();
    }

    private static class TestBadHashFuncClass {
        private final String name;

        public TestBadHashFuncClass(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name.charAt(0);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj instanceof TestBadHashFuncClass) {
                TestBadHashFuncClass o = ((TestBadHashFuncClass) obj);
                return Objects.equals(o.name, this.name);
            }
            return false;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
