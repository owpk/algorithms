import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BackpackQuest {

    public static void main(String[] args) {
        Backpack backpack = new Backpack(15);
        List<Item> itemList = new ArrayList<>(List.of(
                new Item(4, 2),
                new Item(1, 1),
                new Item(10, 6),
                new Item(2, 3)));

        TreeSet<Item> treeSet = new TreeSet<>(itemList);

        treeSet.forEach(backpack::add);

        System.out.println(backpack);
    }

    public static class Backpack {
        private int maxWeight;
        private List<Item> itemList;

        public Backpack(int maxWeight) {
            this.maxWeight = maxWeight;
            itemList = new ArrayList<>();
        }

        public void add(Item item) {
            if (countCurrentWeight() + item.getWeight() < maxWeight)
                itemList.add(item);
        }

        public int countCurrentWeight() {
            return itemList.stream().map(Item::getWeight).reduce(0, Integer::sum);
        }

        @Override
        public String toString() {
            return "Backpack{" +
                    "itemList=" + itemList +
                    '}';
        }
    }

    public static class Item implements Comparable<Item> {
        private int weight;
        private int cost;

        public Item(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Item o) {
            int diff = Math.round(((float) o.cost / o.weight) * 10) -
                    Math.round(((float)  cost / weight) * 10);
            if (diff == 0) return 1;
            return diff;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "weight=" + weight +
                    ", cost=" + cost +
                    '}';
        }
    }
}
