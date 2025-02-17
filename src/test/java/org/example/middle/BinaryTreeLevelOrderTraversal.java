package org.example.middle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.example.TreeNode;


public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        var tree = TreeNode.getTestNode();
        new BinaryTreeLevelOrderTraversal()
            .levelOrder(tree)
            .stream().forEach(it -> System.out.println(it));
    }

    /**
     * Naive solution with recursion
     */
    public List<List<Integer>> levelOrder(TreeNode<Integer,String> root) {
        var result = new ArrayList<List<Integer>>();
        internal(new ArrayDeque<>(List.of(root)), result);
        return result;
    }

    private void internal(Queue<TreeNode<Integer, String>> queue, List<List<Integer>> result) {
        var nextQueue = new ArrayDeque<TreeNode<Integer, String>>();
        var localResult = new ArrayList<Integer>();

        while(!queue.isEmpty()) {
            var next = queue.poll();
            localResult.add(next.key);
            var left = next.left;
            var right = next.right;

            if (left != null)
                nextQueue.add(left);
            
            if (right != null)
                nextQueue.add(right);
        }
        result.add(localResult);

        if (!nextQueue.isEmpty())
            internal(nextQueue, result);
    }

    /**
     * Optimized solution with less memory and 1 queue
     */
    public List<List<Integer>> levelOrderV22(TreeNode<Integer, String> root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        var q = new LinkedList<TreeNode<Integer, String>>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                var cur = q.poll();
                level.add(cur.key);
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
            res.add(level);
        }

        return res;
    }
}
