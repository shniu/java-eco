package io.github.shniu.arts.algothrim.graph.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph
 */
public class Graph {
    // 邻接表
    private Map<Vertex, List<Vertex>> adj;

    public Graph() {
        adj = new HashMap<>();
    }

    public void addVertex(String label) {
        adj.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public List<Vertex> getAdjVertices(String label) {
        return adj.get(new Vertex(label));
    }

    public void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adj.values().forEach(e -> e.remove(v));
        adj.remove(new Vertex(label));
    }

    public void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adj.get(v1).add(v2);
        adj.get(v2).add(v1);
    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> ev1 = adj.get(v1);
        List<Vertex> ev2 = adj.get(v2);
        if (ev1 != null)
            ev1.remove(v2);
        if (ev2 != null)
            ev2.remove(v1);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : adj.keySet()) {
            sb.append(v);
            sb.append(adj.get(v));
        }
        return sb.toString();
    }

    class Vertex {
        String label;

        Vertex(String label) {
            this.label = label;
        }

        //
        // Vertex identity
        //

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((label == null) ? 0 : label.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (label == null) {
                if (other.label != null)
                    return false;
            } else if (!label.equals(other.label))
                return false;
            return true;
        }

        public String toString() {
            return label;
        }

        private Graph getOuterType() {
            return Graph.this;
        }
    }
}
