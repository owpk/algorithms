package org.example;

public class DFS {

    //        10
    //      /   \
    //     5     15
    //   /  \    /  \
    //  3    7  11   20
    public static void main(String[] args) {
        var root = TreeNode.getTestNode();
        DfsTraversal(root);
    }

    public static <K,V> void DfsTraversal(TreeNode<K,V> root) {
        if (root == null)
            return;
        System.out.println(root);

        DfsTraversal(root.getLeft());
        DfsTraversal(root.getRight());
    }
}
