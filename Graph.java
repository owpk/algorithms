package Arlgorithms.graph;

import java.util.*;

public class Graph {
    private final List<Vertex> vertexList;
    private final boolean[][] adjMatrix;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);
        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Invalid label for vetex");
        }
        adjMatrix[startIndex][endIndex] = true;
        adjMatrix[endIndex][startIndex] = true;
    }

    public void addEdges(String startLabel, String secondLabel, String... other) {
        addEdge(startLabel, secondLabel);
        for (String o : other) {
            addEdge(startLabel, o);
        }
    }

    private int indexOf(String startLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(startLabel)) {
                return i;
            }
        }
        return -1;
    }

    public int getVertexSize() {
        return vertexList.size();
    }

    public void display() {
        for (int i = 0; i < getVertexSize(); i++) {
            System.out.println(vertexList.get(i));
            for (int j = 0; j < getVertexSize(); j++) {
                if (adjMatrix[i][j]) {
                    System.out.println(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getVertexSize(); i++) {
            if (adjMatrix[peekIndex][i] && !vertexList.get(i).getVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    // Depth-first search, DFS
    public void dfs(String label) {
        int startIndex = indexOf(label);
        if (startIndex == -1) {
            throw new IllegalArgumentException("invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, stack);
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        System.out.println(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            } else {
                stack.pop();
            }
        }
        resetVertexState();
    }

    // Breadth-first, BFS
    public void bfs(String label) {
        int startIndex = indexOf(label);
        if (startIndex == -1) {
            throw new IllegalArgumentException("invalid start label");
        }
        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        System.out.println(vertex);
        queue.add(vertex);
        vertex.setVisited(true);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            } else {
                queue.remove();
            }
        }
        resetVertexState();
    }

    public void findShortest(String start, String end) {
        int startIndex = indexOf(start);
        int endIndex = indexOf(end);

        Queue<Vertex> queue = new LinkedList<>();
        Vertex startV = vertexList.get(startIndex);
        Vertex endV = vertexList.get(endIndex);

        Node node = new Node(startV);

        visitVertexAndCreatePath(startV, endV, node, queue);

    }

    private void visitVertexAndCreatePath(Vertex startVertex,
                                          Vertex endVertex,
                                          Node node,
                                          Queue<Vertex> queue) {
        queue.add(startVertex);
        startVertex.setVisited(true);

        if (startVertex.getLabel().equals(endVertex.getLabel())) {
            System.out.println(node);
            return;
        }

        while (!queue.isEmpty()) {
            Vertex last = queue.peek();
            startVertex = getNearUnvisitedVertex(last);

            if (startVertex != null) {
                Node nd = new Node(startVertex);
                nd.prevNode = node;
                visitVertexAndCreatePath(startVertex, endVertex, node, queue);
            } else {
                node = nd;
                queue.remove();
            }
        }
        resetVertexState();
    }

    private static class Node {
        private Vertex curr;
        private Node prevNode;

        public Node(Vertex curr) {
            this.curr = curr;
        }
    }


}
