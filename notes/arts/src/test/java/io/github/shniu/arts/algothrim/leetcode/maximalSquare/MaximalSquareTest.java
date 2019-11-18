package io.github.shniu.arts.algothrim.leetcode.maximalSquare;

import org.junit.Test;

public class MaximalSquareTest {

    @Test
    public void maximalSquare() {
        MaximalSquare maximalSquare = new MaximalSquare();
        int square = maximalSquare.maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
        assert square == 4;
    }
}