package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;

public class BFS {

    //        10
    //      /   \
    //     5     15
    //   /  \    /  \
    //  3    7  11   20
    public static void main(String[] args) {
        var root = TreeNode.getTestNode();
        BfsTraversal(root);
    }

    public static <K,V> void BfsMappingTraversal(TreeNode<K,V> node, 
        BiConsumer<Integer, TreeNode<K,V>> consumer) {
        Queue<TreeNode<K, V>> queue = new LinkedList<>();
        queue.add(node);
        var sizeCount = 0;

        while (queue.size() > 0) {
            var next = queue.poll();
            consumer.accept(sizeCount, next);
            sizeCount++;

            var left = next.getLeft();
            if (left != null) {
                queue.add(left);
            }

            var right = next.getRight();
            if (right != null) {
                queue.add(right);
            }
        }
    }

    public static void BfsTraversal(TreeNode<Integer, String> root) {
        System.out.print("[ ");
        BfsMappingTraversal(root, (idx, node) -> System.out.print(node.getKey() + " "));
        System.out.println("]");
    }
    
}
