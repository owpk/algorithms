package org.example.simple;

import java.util.concurrent.atomic.AtomicInteger;

import org.example.TreeNode;

public class MaxBTreeDepth {

    public static void main(String[] args) {
        var o = new MaxBTreeDepth();

        var tree = TreeNode.getTestNode();
        System.out.println(o.maxDepth(tree));
    }

    public int maxDepth(TreeNode root) {
        var count = new AtomicInteger();
        DFS(root, count);
        return count.get();
    }

    private int DFS(TreeNode node, AtomicInteger max) {
        if (node == null)
            return 0;
        
        var left = DFS(node.left, max);
        var right = DFS(node.right, max);
        var curMax = Math.max(left, right);

        max.set(Math.max(max.get(), curMax));
        return curMax + 1;
    }
}
