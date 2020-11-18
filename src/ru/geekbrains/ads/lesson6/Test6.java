package ru.geekbrains.ads.lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testTree();
        testRemoveElement();
    }

    private static void testRemoveElement() throws ExecutionException, InterruptedException {
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
        int numOfCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(
                numOfCores * (1 + 50 / 5));
        List<Integer> coefficient = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            coefficient.add(executorService.submit(createTreeAndCheckIfBalanced).get());
        System.out.printf("balanced / unbalanced : %f", 1f * coefficient.stream().filter(x -> x == 1).count() /
                coefficient.stream().filter(x -> x == 0).count());
        executorService.shutdown();
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

    public static Callable<Integer> createTreeAndCheckIfBalanced = () -> {
        TreeImpl<Integer> tree = new TreeImpl<>();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            tree.add(-100 + r.nextInt(200));
        }
        boolean balanced = tree.isBalanced(tree.getRoot());
        System.out.printf("Tree %d: balanced: %b%n", Thread.currentThread().getId(), balanced);
        return balanced ? 1 : 0;
    };
}
