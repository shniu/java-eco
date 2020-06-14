package io.github.shniu.arts.algothrim.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Graph 的实现
 */
public class Graph {
    private List<Vertex> vertexes;
    private List<Edge> edges;

    public Graph() {
        vertexes = new LinkedList<>();
        edges = new LinkedList<>();
    }

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public void addVertex(Vertex vertex) {
        vertexes.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Vertex> vertices() {
        return vertexes;
    }

    public List<Edge> edges() {
        return edges;
    }

    public void printGraph() {
        for(Edge e : edges) {
            System.out.println(e);
        }
    }
}
