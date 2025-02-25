package org.example;

import java.util.function.Function;

public class Generics {

    public static class A {

    }

    public static class B extends A {

    }

    public static class C extends B {

    }
    
    public static class D extends C {

    }

    public void example() {
        // ? super T — делает метод гибче для входных данных (можно использовать более общие типы).
        // ? extends U — делает метод безопаснее для выходных данных (можно возвращать более конкретные типы).
        // Это называется PECS (Producer Extends, Consumer Super) — принцип, который помогает запомнить, как использовать wildcards:
        // Если ты получаешь данные (producer) — используй extends.
        // Если ты передаешь данные (consumer) — используй super.

        //     any class 'lower' B
        //                 any class 'upper' C
        //     x -> B -> C -> D
        //                  A -> B -> C -> x
        Function<? super B, ? extends C> f = (b) -> new C();
        C c = f.apply(new D());
    }

    public static void main(String[] args) {
        new Generics().example();
    }
    
}
