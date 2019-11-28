package io.github.shniu.arts.algothrim.graph;

/**
 * Edge of the Graph
 */
public class Edge {
    private final String id;
    private final Vertex source;
    private final Vertex target;
    private final int weight;

    public Edge(String id, Vertex source, Vertex target, int weight) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Vertex to() {
        return target;
    }

    public Vertex from() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -" + weight + "-> " + target;
    }
}
