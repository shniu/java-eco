package io.github.shniu.arts.algothrim.graph.traversal;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 用广度优先遍历和深度优先遍历展开下面节点
 * {
 *     name: 'root',
 *     children: [{
 *         name: 'child1',
 *         children: [{
 *             name: 'child1_1',
 *             children: []
 *         }, { name: 'child1_2', children: [] }]
 *     }, {
 *         name: 'child2',
 *         children: [{
 *             name: 'child2_1',
 *             children: []
 *         }]
 *     }, {
 *         name: 'child3',
 *         children: [{
 *             name: 'child2_1',
 *             children: []
 *         }]
 *     }]
 * }
 */
public class JsonTraversal {

    public Set<String> bfs(Node root) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        visited.add(root.name());
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node node : curr.children()) {
                if (node != null)
                    visited.add(node.name());
                    queue.offer(node);
            }
        }
        return visited;
    }

    public Set<String> dfs(Node root) {
        return null;
    }
}

class Node {
    private String name;
    private List<Node> children;

    public Node(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public String name() {
        return name;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> children() {
        return children;
    }
}
