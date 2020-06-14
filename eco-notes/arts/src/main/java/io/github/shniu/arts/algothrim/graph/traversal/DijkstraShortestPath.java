package io.github.shniu.arts.algothrim.graph.traversal;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra 算法
 */
public class DijkstraShortestPath {
    private WeightedDigraph digraph;

    // Source vertex
    private String s;

    // s 到其他节点的权重
    Map<String, Double> distTo;
    // 父节点
    Map<String, String> edgeTo;

    public DijkstraShortestPath(WeightedDigraph digraph, String s) {
        this.digraph = digraph;
        this.s = s;

        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        distTo.put(s, 0.0);

        // Dijkstra 算法
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(s, 0.0));

        while (!priorityQueue.isEmpty()) {
            Node curr = priorityQueue.poll();

            for (WeightedDigraph.DirectedEdge edge : digraph.adj(curr.vertex())) {
                double toCost = distTo.getOrDefault(edge.to(), Double.POSITIVE_INFINITY);
                double newCost = distTo.get(curr.vertex()) + edge.weight();
                if (toCost > newCost) {
                    distTo.put(edge.to(), distTo.get(curr.vertex()) + edge.weight());
                    edgeTo.put("", "");
                    if (priorityQueue.contains(new Node(edge.to(), 0))) {

                    } else {

                    }
                }
            }
        }
    }

    // s -> t 的最小代价
    public double distTo(String t) {
        return distTo.getOrDefault(t, Double.POSITIVE_INFINITY);
    }

    // s -> t 的最优路径
    public Iterable<String> pathTo(String t) {
        return null;
    }

    class Node implements Comparable<Node> {
        private String vertex;
        private double dis;

        public Node(String v, double dis) {
            this.vertex = v;
            this.dis = dis;
        }

        public double dis() {
            return dis;
        }

        public String vertex() {
            return vertex;
        }

        @Override
        public int compareTo(Node other) {
            double diff = this.dis() - other.dis();
            if (diff == 0) return 0;
            else if (diff < 0) return -1;
            else return 1;
        }
    }
}
