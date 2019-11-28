package io.github.shniu.arts.algothrim.graph.sp;

import io.github.shniu.arts.algothrim.graph.Edge;
import io.github.shniu.arts.algothrim.graph.Graph;
import io.github.shniu.arts.algothrim.graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Dijkstra shortest path
 */
public class DijkstraSP {
    private Graph graph;
    private final Vertex s;

    private Set<Vertex> settledNodes; // 已处理节点
    private Set<Vertex> unSettledNodes; // 未处理节点
    // 前驱节点映射，反向查找最优路径
    private Map<Vertex, Vertex> predecessors;
    // s 到其他节点的开销表
    private Map<Vertex, Integer> distance;

    public DijkstraSP(Graph graph, Vertex s) {
        this.graph = graph;
        this.s = s;

        // 初始化
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();

        distance.put(s, 0);
        unSettledNodes.add(s);

        while (!unSettledNodes.isEmpty()) {
            // 在未处理节点中找到开销最小的节点，此处可以使用 PriorityQueue 替代
            Vertex curr = getLowestDistanceVertex(unSettledNodes);
            settledNodes.add(curr);
            unSettledNodes.remove(curr);
            // 更新相邻节点的开销（动态维护 distance）
            findMinimumDistance(curr);
        }
    }

    private void findMinimumDistance(Vertex v) {
        // 找到所有邻接节点
        List<Vertex> adjVertexes = getNeighbors(v);
        for (Vertex t : adjVertexes) {
            int newCost = getShortestDistance(v) + getDistance(v, t);
            // 当前节点前往该邻居更近
            if (getShortestDistance(t) > newCost) {
                distance.put(t, newCost); // 更新代价
                predecessors.put(t, v); // 更新前驱
                unSettledNodes.add(t); // 加入未处理集合中
            }
        }
    }

    private int getShortestDistance(Vertex v) {
        return distance.getOrDefault(v, Integer.MAX_VALUE);
    }

    private int getDistance(Vertex v, Vertex t) {
        for (Edge edge : graph.edges()) {
            if (edge.from().equals(v) && edge.to().equals(t)) return edge.getWeight();
        }
        throw new RuntimeException("Not Valid.");
    }

    private List<Vertex> getNeighbors(Vertex v) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : graph.edges()) {
            if (edge.from().equals(v)
                    && !isSettled(edge.to())) {
                neighbors.add(edge.to());
            }
        }
        return neighbors;
    }

    private boolean isSettled(Vertex v) {
        return settledNodes.contains(v);
    }

    private Vertex getLowestDistanceVertex(Set<Vertex> unSettledNodes) {
        Vertex min = null;
        for (Vertex v : unSettledNodes) {
            if (min == null) min = v;
            else {
                if (getShortestDistance(v) < getShortestDistance(min))
                    min = v;
            }
        }
        return min;
    }

    // s --> t 的最小代价
    public int distTo(Vertex t) {
        return distance.getOrDefault(t, Integer.MAX_VALUE);
    }

    // s --> t 的最优路径
    public Iterable<Vertex> pathTo(Vertex t) {
        List<Vertex> path = new LinkedList<>();
        if (predecessors.get(t) == null) return path;

        Vertex curr = t;
        path.add(curr);
        while (predecessors.get(curr) != null) {
            curr = predecessors.get(curr);
            path.add(curr);
        }
        Collections.reverse(path);
        return path;
    }
}
