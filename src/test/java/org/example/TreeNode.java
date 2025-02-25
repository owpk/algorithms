package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lombok.Getter;

@Getter
public class TreeNode<K, V> {

    //     10
    //     / \
    //   5    15
    //  / \   / \
    // 3   7 11 20
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
    public TreeNode<K, V> left;
    public TreeNode<K, V> right;

    public TreeNode(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public TreeNode(K key) {
        this(key, null);
    }

    public TreeNode(K key, TreeNode<K, V> left, TreeNode<K, V> right) {
        this.key = key;
        this.right = right;
        this.left = left;
    }

    public TreeNode(K key, K left, K right) {
        this.key = key;
        this.right = new TreeNode<>(right);
        this.left = new TreeNode<>(left);
    }

    public TreeNode(K[] keys) {
        this.key = keys[0];
        var root = this;
        Queue<TreeNode<K, V>> queue = new LinkedList<>();
        queue.add(root);

        for (int i = 1; i < keys.length; i += 2) {
            var next = queue.poll();
            if (keys[i] != null) {
                next.setLeft(keys[i], null);
                queue.add(next.getLeft());
            }
            if (i + 1 < keys.length && keys[i + 1] != null) {
                next.setRight(keys[i + 1], null);
                queue.add(next.getRight());
            }
        }
    }

    public void setLeft(TreeNode<K, V> node) {
        this.left = node;
    }

    public void setRight(TreeNode<K, V> node) {
        this.right = node;
    }

    public TreeNode<K, V> setLeft(K key, V value) {
        this.left = new TreeNode<>(key, value);
        return left;
    }

    public TreeNode<K, V> setRight(K key, V value) {
        this.right = new TreeNode<>(key, value);
        return right;
    }

    @Override
    public String toString() {
        return "{ " + key + " : " + value + " }";
    }

    public void print() {
        Queue<TreeNode<K, V>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            TreeNode<K, V> node = queue.poll();
            System.out.println(node);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void prettyPrint() {
        List<String> lines = generateLines(this);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static <K> void prettyPrint(TreeNode<K, ?> root) {
        List<String> lines = generateLines(root);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static List<String> generateLines(TreeNode<?, ?> node) {
        List<String> lines = new ArrayList<>();
        if (node == null) {
            return lines;
        }

        String nodeStr = node.key.toString();
        List<String> leftLines = generateLines(node.left);
        List<String> rightLines = generateLines(node.right);

        int leftWidth = getMaxWidth(leftLines);
        int rightWidth = getMaxWidth(rightLines);
        int nodeWidth = nodeStr.length();
        int totalWidth = Math.max(leftWidth + rightWidth + 1, nodeWidth);

        // Центрируем текущий узел
        String currentLine = center(nodeStr, totalWidth, leftWidth);
        lines.add(currentLine);

        // Добавляем соединительные линии, если есть потомки
        if (!leftLines.isEmpty() || !rightLines.isEmpty()) {
            StringBuilder connectorLine = new StringBuilder();
            int leftConnectorPos = (leftWidth > 0 ? (leftWidth / 2) : 0);
            int rightConnectorPos = (leftWidth + 1 + (rightWidth > 0 ? (rightWidth / 2) : 0));
            if (leftWidth > 0) {
                connectorLine.append(String.format("%" + (leftConnectorPos + 1) + "s", "/"));
            }
            if (rightWidth > 0) {
                int spaces = rightConnectorPos - connectorLine.length();
                connectorLine.append(String.format("%" + spaces + "s", "\\"));
            }
            lines.add(connectorLine.toString());
        }

        // Объединяем линии левого и правого поддеревьев
        int i = 0;
        while (i < leftLines.size() || i < rightLines.size()) {
            String leftLine = i < leftLines.size() ? leftLines.get(i) : "";
            String rightLine = i < rightLines.size() ? rightLines.get(i) : "";
            String paddedLeft = String.format("%-" + (leftWidth + 1) + "s", leftLine);
            String paddedRight = String.format("%-" + (rightWidth + 1) + "s", rightLine);
            lines.add(paddedLeft + " " + paddedRight);
            i++;
        }

        return lines;
    }

    private static int getMaxWidth(List<String> lines) {
        return lines.stream().mapToInt(String::length).max().orElse(0);
    }

    private static String center(String s, int totalWidth, int leftWidth) {
        if (s.length() >= totalWidth) {
            return s;
        }
        int leftPadding = (totalWidth - s.length()) / 2;
        leftPadding = Math.max(leftPadding, leftWidth - s.length() / 2);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < leftPadding; i++) {
            result.append(" ");
        }
        result.append(s);
        while (result.length() < totalWidth) {
            result.append(" ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        TreeNode<Integer, Void> root = new TreeNode<>(10);
        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(20);

        prettyPrint(root);
    }
}