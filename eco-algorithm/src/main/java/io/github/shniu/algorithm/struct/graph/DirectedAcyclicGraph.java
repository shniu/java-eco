package io.github.shniu.algorithm.struct.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/4/19 13
 */
public class DirectedAcyclicGraph {
    private int v;
    private LinkedList<Integer>[] neighbors;

    public DirectedAcyclicGraph(int v) {
        this.v = v;
        //noinspection unchecked
        neighbors = new LinkedList[v];
        for (int i = 0; i < neighbors.length; i++) {
            neighbors[i] = new LinkedList<>();
        }
    }

    // Add edge: s -> t
    public void addEdge(int s, int t) {
        neighbors[s].add(t);
    }

    public List<Integer> topologicalSort() {
        List<Integer> res = new LinkedList<>();

        // 统计每个顶点的入度
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < neighbors[i].size(); j++) {
                Integer w = neighbors[i].get(j);
                inDegree[w]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.remove();
            res.add(i);

            for (int j = 0; j < neighbors[i].size(); j++) {
                int k = neighbors[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }

        return res;
    }
}
