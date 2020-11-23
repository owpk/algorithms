package Arlgorithms.graph;

public class Client {
    public static void main(String[] args) {
        testDFS();
        testBFS();
    }

    private static void testBFS() {
        Graph graph = new Graph(8);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addEdges("A", "B", "C", "D");
        graph.addEdges("B", "E");
        graph.addEdges("E", "H");
        graph.addEdges("C", "F");
        graph.addEdges("D", "G");

        System.out.println("Size of graph is " + graph.getVertexSize());
        graph.display();

        graph.bfs("A");
        //A B C D E F G H
    }

    private static void testDFS() {
        Graph graph = new Graph(7);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdges("A", "B", "C", "D");
        graph.addEdges("B", "E");
        graph.addEdges("D", "F");
        graph.addEdges("F", "G");


        System.out.println("Size of graph is " + graph.getVertexSize());
        graph.display();

        //Алгоритм обхода в глубину
        // Посетить вершину ->
        // Занести вершину в стек ->
        // Отметить вершину как посещенную ->
        // Переход к смежной вершине
        graph.dfs("A");
        //A B E C D F G
    }
}
