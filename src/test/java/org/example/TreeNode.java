package org.example;

import java.util.ArrayDeque;

import lombok.Getter;

@Getter
public class TreeNode<K,V> {

    //        10
    //      /   \
    //     5     15
    //   /  \    /  \
    //  3    7  11   20
    public static TreeNode<Integer, String> getTestNode() {
        var root = new TreeNode<>(10, "abc");
        var left = root.setLeft(5, "root-left");
        var right = root.setRight(15, "root-right");
        left.setLeft(3, "...left");
        left.setRight(7, "...right");
        right.setLeft(11, "left...");
        right.setRight(20, "right...");
        return root;
    }

    public K key;
    public V value;
    public TreeNode<K,V> left;
    public TreeNode<K,V> right;

    public TreeNode(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public TreeNode(K key) {
        this(key, null);
    }

    public TreeNode(K key, TreeNode<K,V> left, TreeNode<K,V> right) {
        this.key = key;
        this.right = right;
        this.left = left;
    }

    public TreeNode(K key, K left, K right) {
        this.key = key;
        this.right = new TreeNode<>(right);
        this.left = new TreeNode<>(left);
    }

    public void setLeft(TreeNode<K,V> node) {
        this.left = node;
    }

    public void setRight(TreeNode<K,V> node) {
        this.right = node;
    }

    public TreeNode<K,V> setLeft(K key, V value) {
        this.left = new TreeNode<>(key, value);
        return left;
    }

    public TreeNode<K,V> setRight(K key, V value) {
        this.right = new TreeNode<>(key, value);
        return right;
    }

    @Override
    public String toString() {
        return "{ " + key + " : " + value + " }";
    }
}