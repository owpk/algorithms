package org.example;

import java.util.Optional;
import java.util.function.Function;

public class Monad<U> {
    private final U u;
    private static final Monad<?> EMPTY = new Monad<>(null);

    private Monad(U u) {
        this.u = u;
    }

    public <T> Monad<T> map(Function<U, T> mapper) {
        if (check()) {
            return empty();
        }
        return Monad.of(mapper.apply(u));
    }

    public <T> Monad<T> flatMap(Function<U, Monad<T>> mapper) {
        if (check()) {
            return empty();
        }
        return mapper.apply(u);
    }

    private <T> boolean check() {
        return this.u == null;
    }

    private static <U> Monad<U> empty() {
        @SuppressWarnings("unchecked")
        Monad<U> t = (Monad<U>) EMPTY;
        return t;
    }

    public static <U> Monad<U> of(U t) {
        return new Monad<>(t);
    }

    public U get() {
        return u;
    }

    public static void main(String[] args) {

        System.out.println(Optional.ofNullable(null)
            .map(it -> it.toString())
            .orElse("null"));

        System.out.println(Monad.of(null)
            .flatMap(it -> Monad.of("abobs: " + it.toString()))
            .get());
    }
}