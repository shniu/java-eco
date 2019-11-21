package io.github.shniu.arts.algothrim.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testDfs() {
        Graph graph = new Graph(10);
        graph.testDfs(new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1},
        });
    }
}