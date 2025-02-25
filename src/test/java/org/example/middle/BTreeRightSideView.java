package org.example.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.example.TreeNode;
import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**  
 *       1 <------
 *      / \
 *     4   5 <--------
 *    / \   \
 *   6   7   8 <-------
 *  /
 * 2 <------
 * 
 * Given the root of a binary tree, imagine yourself standing on 
 * the right side of it, return the values of the nodes you 
 * can see ordered from top to bottom.
 * 
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * 
 * Example 2:
 * Input: root = [1,2,3,4,null,null,null,5]
 * Output: [1,3,4,5]
 * 
 * Example 3:
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 4:
 * Input: root = []
 * Output: []
 */
public class BTreeRightSideView {

    public List<Integer> rightSideView(TreeNode<Integer, String> root) {
        return levelOrder(root);       
    }

    public List<Integer> levelOrder(TreeNode<Integer, String> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return List.of();

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
            res.add(level.get(level.size() - 1));
        }

        return res;
    }

    @ParameterizedTest
    @CsvSource({
        "'[1,2,3,null,5,null,4]', '[1,3,4]'"
    })
    void test(String tree, String expected) {
        var tr = ArrayUtils.toArray(tree, Integer.class);
        var eArr = Arrays.stream(ArrayUtils.toArray(expected, Integer.class)).toList();
        var btree = new TreeNode<Integer, String>(tr);

        btree.prettyPrint();
        var result = rightSideView(btree);
        System.out.println(result);
        Assertions.assertEquals(eArr, result);
    }
    
}
