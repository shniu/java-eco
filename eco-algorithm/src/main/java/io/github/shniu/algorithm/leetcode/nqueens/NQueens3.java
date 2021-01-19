package io.github.shniu.algorithm.leetcode.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/1/19 10
 */
public class NQueens3 {
    private List<List<String>> res = new ArrayList<>();

    // 使用位运算解决
    public List<List<String>> solveNQueens(int n) {

        int col = 0;
        int d1 = 0;
        int d2 = 0;

        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        solveWithBit(queens, 0, col, d1, d2);

        return res;
    }

    private void solveWithBit(int[] queens, int row, int col, int d1, int d2) {
        if (row == queens.length) {
            res.add(toChessboard(queens));
            return;
        }

        int availablePos = ((1 << queens.length) - 1) & (~(col | d1 | d2));

        while (availablePos != 0) {
            int pos = availablePos & (-availablePos);
            availablePos = availablePos & (availablePos - 1);

            // pos 换算成实际的位置
            queens[row] = Integer.bitCount(pos - 1);

            solveWithBit(queens, row + 1, col | pos, (d1 | pos) << 1, (d2 | pos) >> 1);

            queens[row] = -1;
        }
    }

    private List<String> toChessboard(int[] queens) {
        List<String> board = new ArrayList<>();

        for (int queen : queens) {
            char[] row = new char[queens.length];
            Arrays.fill(row, '.');
            row[queen] = 'Q';
            board.add(new String(row));
        }

        return board;
    }

}
