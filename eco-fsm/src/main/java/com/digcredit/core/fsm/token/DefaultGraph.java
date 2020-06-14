package com.digcredit.core.fsm.token;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Default impl
 * Created by Administrator on 2018/12/28 0028.
 */
@Slf4j
public class DefaultGraph<V extends VertexContext, E extends EdgeContext> implements Graph<V, E> {

    private List<Edge<V, E>> edges;
    private Map<String, Vertex<V>> vertexMap;
    boolean isDirected = true;

    public DefaultGraph() {
        edges = new ArrayList<>();
        vertexMap = new LinkedHashMap<>();
    }

    @Override
    public boolean addVertex(V v) {
        //noinspection unchecked
        vertexMap.putIfAbsent(v.getLabel(), newVertex(v));
        return vertexMap.containsKey(v.getLabel());
    }

    @Override
    public Vertex<V> getVertex(V v) {
        return vertexMap.get(v.getLabel());
    }

    @SuppressWarnings("unchecked")
    private Vertex<V> newVertex(V v) {
        Vertex vertex = new Vertex(v.getLabel());
        //noinspection unchecked
        vertex.setContext(v);
        return vertex;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addEdge(V source, V target, E e) {
        Vertex<V> sv = vertexMap.getOrDefault(source.getLabel(), newVertex(source));
        Vertex<V> tv = vertexMap.getOrDefault(target.getLabel(), newVertex(target));
        Edge edge = new Edge(sv, tv, e);
        edges.add(edge);
        return true;
    }

    @Override
    public void bfs(double total) {
        for (Edge<V, E> edge : edges) {
            log.info("{} {} {} {}",
                    edge.getSource().getLabel(),
                    edge.getContext().getType(),
                    edge.getTarget().getLabel(),
                    edge.getContext().getWeight() * total);

            // Todo 生成清单
            // Todo 生成记账单
        }

    }

    @Override
    public List<Vertex<V>> getDirectedAdjacentVertices(Vertex<V> vertex) {
        List<Vertex<V>> adjacentVertices = new ArrayList<>();

        for (Edge<V, E> edge : edges) {
            if (edge.getSource() == vertex) {
                adjacentVertices.add(edge.getTarget());
            }
        }

        return adjacentVertices;
    }
}
