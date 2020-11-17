package ru.geekbrains.ads.lesson2;

import ru.geekbrains.ads.lesson5.RecSortedArrayImpl;

public class TestArray {

    public static void main(String[] args) {
        testDynamicArray();
    }

    private static void testDynamicArray() {
//        Array<Integer> data = new ArrayImpl<>(4);
        Array<Integer> data = new RecSortedArrayImpl<>(4);
        data.add(5);
        data.add(4);
        data.add(3);
        data.add(2);
        data.add(1);

        data.display();
//        data.sortBubble();
//        data.sortSelect();
//        data.sortInsert();
//        data.display();

//        data.remove(Integer.valueOf(3));
//        data.remove(2);
//        data.insert(3, 2);
//        data.display();

//        System.out.println("data[1] = " + data.get(1));
        System.out.println("Find 2: " + data.contains(2));
        System.out.println("Find 222: " + data.contains(222));
    }
}
