package ru.geekbrains.ads.lesson1;

public class Test {

    public static void main(String[] args) {
        String[] array = new String[100];
        String str = "str";
        for (int i = 0; i < array.length; i++) {
            if (str.equals(array[i])) {
                System.out.println("find!");
                break;
            }
        }
    }
}
