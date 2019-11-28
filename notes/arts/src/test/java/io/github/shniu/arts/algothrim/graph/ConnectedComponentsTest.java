package io.github.shniu.arts.algothrim.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectedComponentsTest {

    @Test
    public void connectedComponentNums() {
        ConnectedComponents connectedComponents = new ConnectedComponents();
        int componentNums = connectedComponents.connectedComponentNums(new int[][]{
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2},
                {5},
                {4}
        });

        assert componentNums == 2;

        componentNums = connectedComponents.connectedComponentNums(new int[][]{
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2},
                {5, 6},
                {4},
                {4},
                {}
        });

        assert componentNums == 3;
    }
}