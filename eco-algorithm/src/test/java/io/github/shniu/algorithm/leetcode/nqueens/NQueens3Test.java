package io.github.shniu.algorithm.leetcode.nqueens;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/1/19 10
 */
public class NQueens3Test {

    @Test
    public void solveNQueens() {

        NQueens3 nQueens3 = new NQueens3();
        List<List<String>> r = nQueens3.solveNQueens(4);
        System.out.println(r);

        r = nQueens3.solveNQueens(6);
        System.out.println(r);
    }
}