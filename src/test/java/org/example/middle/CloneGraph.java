package org.example.middle;

import java.util.HashMap;

import org.example.GraphNode;

/*
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * 
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * 
 * Test case format:
 * 
 * For simplicity, each node's value is the same as the node's index (1-indexed). 
 * For example, the first node with val == 1, the second node with val == 2, and so on. 
 * The graph is represented in the test case using an adjacency list.
 * 
 * An adjacency list is a collection of unordered lists used to represent a finite graph. 
 * Each list describes the set of neighbors of a node in the graph.
 * 
 * The given node will always be the first node with val = 1. 
 * You must return the copy of the given node as a reference to the cloned graph.
 * 
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. 
 * The graph consists of only one node with val = 1 and it does not have any neighbors.
 * 
 * Example 3:
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 */
public class CloneGraph {

    //     1
    //    / \
    //   2 - 3
    public static void main(String[] args) {
        var o = new CloneGraph();

        var node1 = new GraphNode(1);
        var node2 = new GraphNode(2);
        var node3 = new GraphNode(3);

        node1.neighbors.add(node2);
        node1.neighbors.add(node3);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node1);
        node3.neighbors.add(node2);

        System.out.println(node1);

        var clone = o.cloneGraph(node1, new HashMap<>());
        System.out.println(clone);
    }

    public GraphNode cloneGraph(GraphNode node, HashMap<Integer, GraphNode> visited) {
        if (node == null)
            return null;

        var clone = new GraphNode(node.val);
        var neighbors = node.neighbors;
        visited.put(node.val, clone);

        for (var n: neighbors) {
            if (!visited.containsKey(n.val))
                clone.neighbors.add(cloneGraph(n, visited));
            else
                clone.neighbors.add(visited.get(n.val));
            
        }

        return clone;
    }
}
