package io.github.shniu.arts.algothrim.graph.sort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 拓扑排序
 * https://github.com/billryan/algorithm-exercise/blob/master/zh-hans/graph/topological_sorting.md
 */
public class TopologicalSorting {

    /**
     * 使用 DFS 进行拓扑排序
     */
    public List<DirectedGraphNode> topSort1(List<DirectedGraphNode> graph) {
        List<DirectedGraphNode> res = new ArrayList<>();
        // 得到节点和入度的映射关系
        Map<DirectedGraphNode, Integer> inDegreeMap = getNodeInDegree(graph);

        // 反复找入度为 0 的节点，并标记删除掉
        for (Map.Entry<DirectedGraphNode, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) dfsSort(inDegreeMap, entry.getKey(), res);
        }

        return res;
    }

    // 节点和入度的映射关系
    private Map<DirectedGraphNode, Integer> getNodeInDegree(List<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> inDegreeMap = new HashMap<>();
        for (DirectedGraphNode node :
                graph) {
            inDegreeMap.putIfAbsent(node, 0); // 初始化所有节点的入度为 0
            for (DirectedGraphNode neighbor : node.neighbors) {
                inDegreeMap.put(neighbor, inDegreeMap.getOrDefault(neighbor, 0) + 1);
            }
        }
        return inDegreeMap;
    }

    // DFS
    private void dfsSort(Map<DirectedGraphNode, Integer> inDegreeMap,
                         DirectedGraphNode node,
                         List<DirectedGraphNode> res) {
        res.add(node);
        // node 的入度被标记为 -1，表示删除
        inDegreeMap.put(node, inDegreeMap.get(node) - 1);

        // 更新邻近节点的入度
        for (DirectedGraphNode neighbor : node.neighbors) {
            inDegreeMap.put(neighbor, inDegreeMap.get(neighbor) - 1);
            // DFS 入度为 0 的节点，剪枝其他节点
            if (inDegreeMap.get(neighbor) == 0)
                dfsSort(inDegreeMap, neighbor, res);
        }
    }

    /**
     * 使用 BFS 进行拓扑排序
     */
    public List<DirectedGraphNode> topSort2(List<DirectedGraphNode> graph) {
        List<DirectedGraphNode> res = new ArrayList<>();
        // 得到节点和入度的映射关系
        Map<DirectedGraphNode, Integer> inDegreeMap = getNodeInDegree(graph);

        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (Map.Entry<DirectedGraphNode, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) queue.offer(entry.getKey());
        }

        bfsSort(inDegreeMap, queue, res);
        return res;
    }

    // BFS
    private void bfsSort(Map<DirectedGraphNode, Integer> inDegreeMap,
                         Queue<DirectedGraphNode> queue,
                         List<DirectedGraphNode> res) {
        while (!queue.isEmpty()) {
            DirectedGraphNode curr = queue.poll();
            res.add(curr);

            for (DirectedGraphNode neighbor :
                    curr.neighbors) {
                inDegreeMap.put(neighbor, inDegreeMap.get(neighbor) - 1);
                if (inDegreeMap.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

    }

    /**
     * DFS
     * 核心思想：从图中任意一个节点递归，直至将其所有邻接节点入栈后再对自己入栈，
     * 这样可保证有依赖的节点一定在其父节点后面出栈，遍历完图中所有节点即可得最终有效结果
     */
    public List<DirectedGraphNode> topSort3(List<DirectedGraphNode> graph) {
        List<DirectedGraphNode> res = new ArrayList<>();
        // 辅助标记，已访问过的节点必须要再次访问
        Set<DirectedGraphNode> visited = new HashSet<>();
        // stack 来存储依赖的先后顺序
        Deque<DirectedGraphNode> stack = new LinkedList<>();

        for (DirectedGraphNode node : graph) {
            dfsHelper(visited, stack, node);
        }

        // 输出结果
        while (!stack.isEmpty())
            res.add(stack.removeFirst());

        return res;
    }

    private void dfsHelper(Set<DirectedGraphNode> visited,
                           Deque<DirectedGraphNode> stack,
                           DirectedGraphNode node) {
        // terminator
        if (!visited.contains(node)) {
            // process current logic
            visited.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                // drill down: neighbor node
                dfsHelper(visited, stack, neighbor);
            }
            // process current logic
            stack.addFirst(node);
        }
    }

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
}
