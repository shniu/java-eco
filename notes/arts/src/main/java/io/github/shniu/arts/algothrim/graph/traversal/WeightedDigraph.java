package io.github.shniu.arts.algothrim.graph.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加权有向图
 */
public class WeightedDigraph {
    private int V;
    private int E;
    private Map<Vertex, List<DirectedEdge>> adj;

    public WeightedDigraph(int V) {
        this.V = V;
        adj = new HashMap<>(V);
    }

    public void addEdge(DirectedEdge edge) {
        Vertex vertex = new Vertex(edge.from());
        List<DirectedEdge> edgeList = adj.getOrDefault(vertex, new ArrayList<>());
        edgeList.add(edge);
        adj.put(vertex, edgeList);
        this.E++;
    }

    public Iterable<DirectedEdge> adj(String v) {
        return adj.getOrDefault(new Vertex(v), Collections.emptyList());
    }

    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> edges = new ArrayList<>();
        for (List<DirectedEdge> edgeList : adj.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    static class DirectedEdge {
        private String f;
        private String t;
        private double weight;

        public DirectedEdge(String f, String t, double weight) {
            this.f = f;
            this.t = t;
            this.weight = weight;
        }

        public String from() {
            return f;
        }

        public String to() {
            return t;
        }

        public double weight() {
            return weight;
        }
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

        private WeightedDigraph getOuterType() {
            return WeightedDigraph.this;
        }
    }
}
