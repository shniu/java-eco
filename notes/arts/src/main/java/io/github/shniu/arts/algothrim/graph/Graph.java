package io.github.shniu.arts.algothrim.graph;

import java.util.LinkedList;

/**
 * Graph 的实现
 */
public class Graph {
    // 顶点个数
    private int v;
    // 邻接表
    private LinkedList<Edge> adj[];

    public Graph(int v) {
        this.v = v;
        //noinspection unchecked
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }
}
