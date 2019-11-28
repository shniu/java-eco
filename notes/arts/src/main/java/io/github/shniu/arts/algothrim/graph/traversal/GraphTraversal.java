package io.github.shniu.arts.algothrim.graph.traversal;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Traversal
 */
public class GraphTraversal {

    /**
     * DFS 非递归实现的核心是借助Stack和已访问节点的集合。
     *
     * @param graph 图
     * @param root  搜索开始的节点
     * @return dfs 的节点
     */
    public static Set<String> dfs(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Deque<String> stack = new LinkedList<>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            String top = stack.removeFirst();
            if (!visited.contains(top)) {
                visited.add(top);
                for (Graph.Vertex adjVertex : graph.getAdjVertices(top)) {
                    stack.addFirst(adjVertex.label);
                }
            }
        }

        return visited;
    }

    // 递归实现
    public static Set<String> dfs2(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        dfsHelper(graph, visited, root);
        return visited;
    }

    private static void dfsHelper(Graph graph, Set<String> visited, String vertex) {
        // terminator
        if (visited.contains(vertex)) return;

        // process current logic
        visited.add(vertex);

        // drill down
        for (Graph.Vertex adjVertex : graph.getAdjVertices(vertex)) {
            dfsHelper(graph, visited, adjVertex.label);
        }
    }

    /**
     * BFS 的实现核心是借助队列和已访问节点的集合。
     *
     * @param graph 图
     * @param root  搜索开始的节点
     * @return bfs 的节点
     */
    public static Set<String> bfs(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Graph.Vertex adjVertex : graph.getAdjVertices(vertex)) {
                if (!visited.contains(adjVertex.label)) {
                    visited.add(adjVertex.label);
                    queue.offer(adjVertex.label);
                }
            }
        }

        return visited;
    }
}
