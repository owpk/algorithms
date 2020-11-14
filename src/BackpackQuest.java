import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;

public class BackpackQuest {

    public static void main(String[] args) {
        Backpack backpack = new Backpack(15);
        List<Item> itemList = new ArrayList<>(List.of(
                new Item(4, 2),
                new Item(1, 1),
                new Item(10, 6),
                new Item(2, 3)));

        //1 Подход через TreeSet и Comparable<Item>
        TreeSet<Item> treeSet = new TreeSet<>(itemList);
        for (Item item : treeSet) {
            if (backpack.countCurrentWeight() + item.getWeight() < backpack.getMaxWeight())
                backpack.add(item);
            else break;
        }
        System.out.println(backpack);

        //2 Подход через рекурсию
        backpack.itemList  = null;
        System.out.println(backpack.getBestOption(itemList));

    }

    public static class Backpack {
        private int maxWeight;
        private int cost;
        private List<Item> itemList;

        public Backpack(int maxWeight) {
            this.maxWeight = maxWeight;
            itemList = new ArrayList<>();
        }

        public List<Item> getBestOption(List<Item> items) {
            if (itemList == null) {
                if (calculate(Item::getWeight, items) < maxWeight) {
                    itemList = items;
                    cost = calculate(Item::getCost, items);
                }
            }
            else {
                if(calculate(Item::getWeight, items) <= maxWeight && calculate(Item::getCost, items) > cost) {
                    itemList = items;
                    cost = calculate(Item::getCost, items);
                }
            }

            for (int i = 0; i < items.size(); i++) {
                List<Item> newSet = new ArrayList<>(items);
                newSet.remove(i);
                itemList = getBestOption(newSet);
            }
            return itemList;
        }

        public void add(Item item) {
            itemList.add(item);
        }

        public int countCurrentWeight() {
            return calculate(Item::getWeight, itemList);
        }

        public int countCurrentCost() {
            return calculate(Item::getCost, itemList);
        }

        private int calculate(Function<Item, Integer> function, List<Item> list) {
            return list.stream().map(function).reduce(0, Integer::sum);
        }

        public int getMaxWeight() {
            return maxWeight;
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

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Item o) {
            int diff = Math.round(((float) o.cost / o.weight) * 10) -
                    Math.round(((float) cost / weight) * 10);
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
