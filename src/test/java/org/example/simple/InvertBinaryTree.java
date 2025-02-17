package org.example.simple;

import org.example.BFS;
import org.example.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 * 
 * Example 1:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * 
 * Example 2:
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * 
 * Example 3:
 * Input: root = []
 * Output: []
 * 
 */
public class InvertBinaryTree {

    public TreeNode<Integer, String> invertTree(TreeNode<Integer, String> root) {
        swap(root);
        return root;
    }

    private void swap(TreeNode<Integer, String> node) {
        if (node == null)
            return;
        
        var tmp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(tmp);
        swap(node.getLeft());
        swap(node.getRight());
    }

    @Test
    void test() {
        var root = new TreeNode<>(10, "abc");
        var left = root.setLeft(5, "root-left");
        var right = root.setRight(15, "root-right");
        left.setLeft(3, "...left");
        left.setRight(7, "...right");
        right.setLeft(11, "left...");
        right.setRight(20, "right...");

        swap(root);
        BFS.BfsTraversal(root);
    }

}
