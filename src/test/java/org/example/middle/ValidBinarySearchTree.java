package org.example.middle;

import org.example.TreeNode;
import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given the root of a binary tree, determine if it is a valid binary search
 * tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's
 * key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 * 
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode<Integer, Integer> root) {
        return isValidBSTHelper(root, null, null);
    }
    
    private boolean isValidBSTHelper(TreeNode<Integer, Integer> node, 
        Integer min, Integer max) {

        if (node == null)
            return true;
    
        if ((min != null && node.key <= min) || (max != null && node.key >= max))
            return false;
        
        return isValidBSTHelper(node.left,  min,      node.key) &&
               isValidBSTHelper(node.right, node.key, max);
    }

    @ParameterizedTest
    @CsvSource({
        "'[32,26,47,19,null,null,56,null,27]', false",
        "'[34,-6,null,-21]', true",
        "'[5,14,null,1]', false",
        "'[10,5,15,null,null,6,20]', false",
        "'[0,-1]', true",
        "'[2,1,3]', true",
        "'[5,1,4,null,null,3,6]', false",
        "'[5,4,6,null,null,3,7]', false"
    })
    void test(String array, boolean expected) {
        var arr = ArrayUtils.toArray(array, Integer.class);
        var node = new TreeNode<Integer, Integer>(arr);
        node.prettyPrint();
        System.out.println("------");

        var result = isValidBST(node);
        Assertions.assertEquals(expected, result);
    }
}
