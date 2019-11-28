package io.github.shniu.arts.algothrim.graph.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 最短路径算法实现
 */
public class UnweightedShortestPath {
    private Graph graph;

    // Source Vertex
    private String s;
    private Set<String> visited;

    // BFS 时，每个节点和前驱节点的映射关系
    private Map<String, String> preVertexMap;
    private Map<String, Integer> distance;

    public UnweightedShortestPath(Graph graph, String s) {
        this.graph = graph;
        this.s = s;

        // 使用 LinkedHashSet 保存 BFS 的遍历顺序
        visited = new LinkedHashSet<>();
        preVertexMap = new HashMap<>();
        distance = new HashMap<>();

        bfs(s);
    }

    private void bfs(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        preVertexMap.put(s, s);
        distance.put(s, 0);

        while (!queue.isEmpty()) {
            String vertex = queue.poll();

            for (Graph.Vertex adjVertex : graph.getAdjVertices(vertex)) {
                if (!visited.contains(adjVertex.label)) {
                    queue.offer(adjVertex.label);
                    visited.add(adjVertex.label);
                    preVertexMap.put(adjVertex.label, vertex);
                    distance.put(adjVertex.label, distance.get(vertex) + 1);
                }
            }
        }
    }

    public Iterable<String> pathTo(String t) {
        List<String> res = new ArrayList<>();
        if (!visited.contains(t)) return res;

        String curr = t;
        while (!s.equals(curr)) {
            res.add(curr);
            curr = preVertexMap.get(curr);
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public int distTo(String t) {
        return distance.getOrDefault(t, -1);
    }
}
