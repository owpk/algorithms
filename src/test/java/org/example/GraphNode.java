package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import lombok.ToString;

public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<GraphNode>();
    }

    public GraphNode(int val) {
        this.val = val;
        neighbors = new ArrayList<GraphNode>();
    }

    public GraphNode(int val, ArrayList<GraphNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        var visited = new HashMap<Integer, GraphNode>();
        var result = new ArrayList<GraphNode>();
        traverse(this, visited, result);
        return result.stream()
            .map(it -> Integer.toString(it.val))
            .collect(Collectors.joining(" -> "));
    }

    public void traverse(GraphNode node, HashMap<Integer, GraphNode> visited, List<GraphNode> nodes) {
        if (node == null)
            return;
    
        var neighbors = node.neighbors;
        visited.put(node.val, node);

        nodes.add(node);

        for (var n: neighbors) {
            if (!visited.containsKey(n.val))
                traverse(n, visited, nodes);
        }
    }
}
