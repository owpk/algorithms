package ru.geekbrains.ads.lesson6;

public class Test6 {

    public static void main(String[] args) {
//        testTree();
        testRemoveElement();
    }

    private static void testRemoveElement() {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(25);
        tree.add(66);
        tree.add(15);
        tree.add(5);
        tree.add(20);
        tree.add(45);
        tree.add(30);
        tree.add(55);
        tree.add(32);

        tree.remove(25);
        tree.display();
    }

    private static void testTree() {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(50);
        tree.add(66);
        tree.add(40);
        tree.add(55);
        tree.add(70);
        tree.add(31);
        tree.add(45);
        tree.add(67);
        tree.add(81);

        System.out.println("Find 70: " + tree.contains(70));
        System.out.println("Find 700: " + tree.contains(700));

        tree.display();
//        tree.traverse(Tree.TraverseMode.IN_ORDER, System.out::println);
//        tree.traverse(Tree.TraverseMode.PRE_ORDER, System.out::println);
//        tree.traverse(Tree.TraverseMode.POST_ORDER, System.out::println);
    }
}
