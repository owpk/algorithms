package ru.geekbrains.ads.lesson3.deque;


import ru.geekbrains.ads.lesson3.myqueue.Queue;

public interface Deque<E> extends Queue<E> {

    boolean insertLeft(E value);
    boolean insertRight(E value);

    E removeLeft();
    E removeRight();
}