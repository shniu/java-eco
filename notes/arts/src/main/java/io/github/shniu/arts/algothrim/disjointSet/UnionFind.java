package io.github.shniu.arts.algothrim.disjointSet;

import java.util.Arrays;

/**
 * 并查集的简单实现
 */
public class UnionFind {
    private int count;
    private int[] parent;

    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 查找元素 p 所在的集合的根节点（也就是该集合的代表元素）
     *
     * @param p 要查找的元素
     * @return 代表元素
     */
    public int find(int p) {
        while (p != parent[p]) {
            // 这是为了加速查找, 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    /**
     * 合并两个元素所在的集合
     *
     * @param p 元素p
     * @param q 元素q
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parent: ");
        sb.append(Arrays.toString(parent));
        sb.append(", Count: ");
        sb.append(count);
        return sb.toString();
    }
}
