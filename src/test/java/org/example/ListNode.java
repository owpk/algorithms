package org.example;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ListNode implements Comparable<ListNode> {
    private Integer val;
    private ListNode next;
    public Integer val() {
        return val;
    }

    public void val(Integer val) {
        this.val = val;
    }

    public void next(ListNode next) {
        this.next = next;
    }

    public ListNode next() {
        return next;
    }

    public ListNode() {}

    public ListNode(Integer...args) {
        this.addAll(args);
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public Integer get(int idx) {
        var ct = new AtomicInteger();
        AtomicReference<Integer> ref = new AtomicReference<>();
        recursiveOp(this, it -> { var ind = ct.incrementAndGet(); if (ind == idx) ref.set(it.val);  });
        return ref.get();
    }
    
    public void addLast(ListNode node) {
        ListNode next = this;
        ListNode last = null;
        while (next != null) {
            if (next.next == null) {
                last.next = node;
                return;
            }
            next = next.next;
            last = next;
        }
    }

    public void addAll(Integer...val) {
        if (val != null) {
            for (Integer val2 : val) {
                add(val2);
            }
        }
    }

    public ListNode add(Integer val) {
        if (val == null) 
            return this;

        if (this.val == null) {
            this.val = val;
            return this;
        }

        var next = new ListNode(val);
        var nextNode = this;
        while(nextNode.next != null) {
            nextNode = nextNode.next;
        }
        nextNode.next = next;
        return next;
    }

    public int getSize() {
        var ct = new AtomicInteger();
        recursiveOp(this, it -> { ct.incrementAndGet(); });
        return ct.get();
    }

    public void forEach(Consumer<Integer> consumer) {
        recursiveOp(this, (node) -> consumer.accept(node.val));
    }

    public void forEachCount(BiConsumer<Integer, Integer> consumer) {
        var counter = new AtomicInteger();
        recursiveOp(this, (node) -> {
            consumer.accept(node.val, counter.incrementAndGet());
        });
    }

    @Override
    public String toString() {
        var array = new ArrayList<Integer>();
        recursiveOp(this, node -> { if (node != null && node.val != null) array.add(node.val); });
        return "[" + array.stream()
            .map(it -> Integer.toString(it))
            .collect(Collectors.joining(" -> ")) + "]";
    }

    private ListNode recursiveOp(ListNode node, Consumer<ListNode> onNext) {
        if (node != null) {
            onNext.accept(node);
            return recursiveOp(node.next, onNext);
        } return node;
    }

    @Override
    public int compareTo(ListNode o) {
        if (o == null)
            return 1;
        if (o.val == null)
            return 1;
        if (this.val == null)
            return -1;
        else return Integer.compare(this.val, o.val);
    }
}
