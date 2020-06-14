package com.digcredit.core.fsm.token;

import java.util.List;

/**
 * The root interface in the graph hierarchy
 * https://github.com/mihneadb/Graphs/blob/master/Graph.java
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 * @author shniu
 */
public interface Graph<V extends VertexContext, E extends EdgeContext> {

    /**
     * Add vertex
     *
     * @param v vertex context, which holder the data of the vertex
     * @return succeed or not
     */
    boolean addVertex(V v);

    Vertex<V> getVertex(V v);

    /**
     * Add edge
     *
     * @param source source vertex context
     * @param target target vertex context
     * @param e      weight of the edge
     * @return succeed or not
     */
    boolean addEdge(V source, V target, E e);

    void bfs(double total);

    List<Vertex<V>> getDirectedAdjacentVertices(Vertex<V> vertex);

}
