package io.github.shniu.arts.algothrim.graph.traversal;

import org.junit.Test;

public class GraphTest {

    /**
     *      v2 -- v6
     *    /
     * v1      v4
     *    \   /
     *     v3 -- v5
     */
    @Test
    public void testGraph() {
        Graph graph = new Graph();
        graph.addVertex("v1");
        graph.addVertex("v2");
        graph.addVertex("v3");
        graph.addVertex("v4");
        graph.addVertex("v5");
        graph.addVertex("v6");
        graph.addEdge("v1", "v2");
        graph.addEdge("v1", "v3");
        graph.addEdge("v3", "v4");
        graph.addEdge("v3", "v5");
        graph.addEdge("v2", "v6");

        String p = graph.print();
        System.out.println(p);
    }

    @Test
    public void testVertex() {
        Graph graph = new Graph();
        Graph.Vertex vertex1 = graph.new Vertex("v1");
        Graph.Vertex vertex2 = graph.new Vertex("v1");
        assert vertex1.equals(vertex2);
        assert vertex1 != vertex2;
    }

}