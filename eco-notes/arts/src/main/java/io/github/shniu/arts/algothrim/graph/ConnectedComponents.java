package io.github.shniu.arts.algothrim.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求解无向图的连通分量
 */
public class ConnectedComponents {

    /**
     * 无向图 graph, graph[i]表示图中与节点i相连的所有节点
     * <p>
     * 如 [[1,3], [0,2], [1,3], [0,2], [5], [4]]
     * 表示：
     * 0 -- 1
     * |    |
     * 3 -- 2   4 -- 5
     */
    public int connectedComponentNums(int[][] graph) {
        Integer[] colors = new Integer[graph.length];

        int color = 0;
        for (int v = 0; v < graph.length; v++) {
            // 还没有染色
            if (colors[v] == null) {
                color++;
                floodFill(graph, v, colors, color);
            }
        }
        return color;
        // return new HashSet<>(Arrays.asList(colors)).size();
    }

    private void floodFill(int[][] graph, int v, Integer[] colors, int color) {
        colors[v] = color;  // 染色
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int adj : graph[curr]) {
                if (colors[adj] == null) {
                    colors[adj] = color;  // 染色
                    queue.offer(adj);
                }
            }
        }
    }
}
