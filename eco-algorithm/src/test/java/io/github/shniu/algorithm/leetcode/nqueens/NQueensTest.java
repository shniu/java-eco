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

    @Test
    public void test() {
        System.out.println(Integer.toBinaryString(-5));
        System.out.println((1 << 8) - 1);
        System.out.println(Integer.toBinaryString((1 << 8) - 1));

        System.out.println(Integer.toBinaryString(8 & -8));
        System.out.println(Integer.toBinaryString(5 & -5));
        System.out.println(Integer.toBinaryString(4 & -4));
        System.out.println(Integer.toBinaryString(3 & -3));

        System.out.println(Integer.toBinaryString((1 << 8) - 1 & (~(0 | 0 | 0))));

        int avail = (1 << 8) - 1 & (~(0 | 0 | 0));
        // 00000001
        System.out.println(Integer.toBinaryString(avail & -avail));
    }

}
