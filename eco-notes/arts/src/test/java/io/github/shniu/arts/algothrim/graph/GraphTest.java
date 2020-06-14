package io.github.shniu.arts.algothrim.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testGraph() {
        Graph graph = new Graph();

        Vertex v1 = new Vertex("Node_1", "a");
        Vertex v2 = new Vertex("Node_2", "b");
        graph.addVertex(v1);
        graph.addVertex(v2);
        Vertex v3 = new Vertex("Node_3", "c");
        graph.addVertex(v3);
        Vertex v4 = new Vertex("Node_4", "d");
        graph.addVertex(v4);
        Vertex v5 = new Vertex("Node_5", "e");
        graph.addVertex(v5);
        Vertex v6 = new Vertex("Node_6", "f");
        graph.addVertex(v6);
        Vertex v7 = new Vertex("Node_7", "g");
        graph.addVertex(v7);

        graph.addEdge(new Edge("Edge_1", v1, v2, 5));
        graph.addEdge(new Edge("Edge_2", v1, v3, 3));

        graph.addEdge(new Edge("Edge_3", v2, v5, 7));
        graph.addEdge(new Edge("Edge_4", v3, v4, 2));
        graph.addEdge(new Edge("Edge_4", v4, v5, 2));
        graph.addEdge(new Edge("Edge_4", v5, v6, 4));
        graph.addEdge(new Edge("Edge_4", v5, v7, 5));
        graph.addEdge(new Edge("Edge_4", v6, v7, 2));

        graph.printGraph();
    }
}