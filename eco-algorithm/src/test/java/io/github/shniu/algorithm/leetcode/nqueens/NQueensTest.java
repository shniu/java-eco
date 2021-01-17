package io.github.shniu.algorithm.leetcode.nqueens;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/1/17 21
 */
public class NQueensTest {

    @Test
    public void test_nqueens() {
        NQueens nQueens = new NQueens();
        List<List<String>> queens = nQueens.solveNQueens(6);

        System.out.println(queens);
    }

}
