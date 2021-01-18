package io.github.shniu.algorithm.leetcode.nqueens;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/1/18 13
 */
public class NQueensIITest {

    @Test
    public void test_totalNQueens() {
        NQueensII queensII = new NQueensII();

        assertEquals(2, queensII.totalNQueens(4));
    }
}