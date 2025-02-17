package org.example.simple;

import java.util.concurrent.atomic.AtomicInteger;

import org.example.TreeNode;

public class DiameterOfBinaryTree {

    public static void main(String[] args) {
        // var root = TreeNode.getTestNode();

        //       5
        //      /
        //     2
        //    / \
        //   4   3
        //        \
        //         1
        TreeNode<Integer, String> root = new TreeNode<>(4);

        root.left = new TreeNode<>(-7);
        root.right = new TreeNode<>(-3);

        root.right.right = new TreeNode<>(-3, -4, null);

        root.right.left = new TreeNode<>(-9);
        root.right.left.left = new TreeNode<>(9);
        root.right.left.right = new TreeNode<>(-7);
        root.right.left.left.left = new TreeNode<>(6);
        root.right.left.left.left.left = new TreeNode<>(0, -1, null);
        root.right.left.left.left.right = new TreeNode<>(6, -4, null);
        root.right.left.right.left = new TreeNode<>(-6, 5, null);
        root.right.left.right.right = new TreeNode<>(-6, new TreeNode<>(9, -2, null), null);

        var d = new DiameterOfBinaryTree();
        var result = d.diameterOfBinaryTree(root);

        System.out.println(result);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        var counter = new AtomicInteger();
        DFS(root, counter);
        return counter.get();
    }

    /**
     * O(n)	The code recursively traverses each node in the binary tree once, 
     * so the time complexity is O(n), where n is the number of nodes in the tree.
     */
    private int DFS(TreeNode node, AtomicInteger max) {
        if (node == null)
            return 0;
        
        var right = node.right;
        var left = node.left;

        int leftCount = DFS(left, max);
        int rightCount = DFS(right, max);

        max.set(Math.max(leftCount + rightCount, max.get()));
        return Math.max(leftCount, rightCount) + 1;
    }
}
