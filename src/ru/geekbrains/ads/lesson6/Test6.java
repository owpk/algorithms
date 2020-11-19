package ru.geekbrains.ads.lesson6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Predicate;

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
        final int TREES_NUM = 20;
        final CountDownLatch cdl = new CountDownLatch(TREES_NUM);
        List<Integer> resultList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < TREES_NUM; i++)
            executorService.submit(createTreeAndCheckIfBalanced(i, resultList, cdl));
        cdl.await();
        System.out.printf("(balanced / unbalanced) k = %f", 1f * count(resultList, 1) /
                count(resultList, 0));
        executorService.shutdownNow();
    }

    private static long count(List<Integer> list, int num) {
        return list.stream().filter(x -> x == num).count();
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

    public static Runnable createTreeAndCheckIfBalanced(int id, List<Integer> resultList, CountDownLatch cdl) {
        return () -> {
            TreeImpl<Integer> tree = new TreeImpl<>();
            Random r = new Random();
            while (tree.getCurrentDepth() < tree.MAX_DEPTH) {
                tree.add(-25 + r.nextInt(50));
            }
            boolean balanced = tree.isBalanced(tree.getRoot());
            synchronized (cdl) {
                System.out.printf("Tree %d: balanced: %b%n", id, balanced);
                tree.display();
            }
            if (balanced) resultList.add(1);
            else resultList.add(0);
            cdl.countDown();
        };
    }
}
