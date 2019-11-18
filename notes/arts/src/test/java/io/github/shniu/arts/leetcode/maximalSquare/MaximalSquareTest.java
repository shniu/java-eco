package io.github.shniu.arts.leetcode.maximalSquare;

import org.junit.Test;

import static org.junit.Assert.*;

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