package io.github.shniu.arts.algothrim.leetcode.isGraphBipartite;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/is-graph-bipartite
 * 785. 判断二分图
 */
public class GraphBipartite {

    public boolean isBipartite(int[][] graph) {
        // 顶点是否访问过
        boolean[] visited = new boolean[graph.length];
        // 顶点的颜色
        int[] colors = new int[graph.length];

        for (int v = 0; v < graph.length; v++) {
            if (!visited[v]) {
                if (!bfs(graph, v, colors, visited)) return false;
            }
        }

        return true;
    }

    private boolean bfs(int[][] graph, int v, int[] colors, boolean[] visited) {
        visited[v] = true;
        colors[v] = 0;  // 起始节点颜色
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int t : graph[curr]) {
                if (!visited[t]) {
                    queue.offer(t);
                    visited[t] = true;
                    colors[t] = 1 - colors[curr];  // 相邻节点着色为其他颜色
                } else if (colors[curr] == colors[t]) {
                    // 已访问过的节点颜色相同，说明不是二分图
                    return false;
                }
            }
        }

        return true;
    }
}
