package io.github.shniu.arts.algothrim.leetcode.numberOfIslands;

import org.junit.Test;

public class NumberIslandsTest {

    @Test
    public void numIslands() {
        NumberIslands numberIslands = new NumberIslands();
        int numIslands = numberIslands.numIslands11(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        });

        assert numIslands == 1;
    }

    @Test
    public void testNumIslandsWithBFS() {
        NumberIslands numberIslands = new NumberIslands();
        int islands = numberIslands.numIslands13(new char[][]{
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '1', '1', '0', '1'}
        });
        assert islands == 1;
    }
}