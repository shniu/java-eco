package io.github.shniu.algorithm.leetcode.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/n-queens/
 * 这种解法有优化空间.
 *
 * @author niushaohan
 * @date 2021/1/18 13
 */
public class NQueens2 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        // 列
        Set<Integer> colsPlaced = new HashSet<>();
        // 主对角线
        Set<Integer> mainDiagonalsPlaced = new HashSet<>();
        // 副对角线
        Set<Integer> subDiagonalsPlaced = new HashSet<>();

        int[] queens = new int[n];

        populateChessboard(res, 0, queens, colsPlaced, mainDiagonalsPlaced, subDiagonalsPlaced);

        return res;
    }

    private void populateChessboard(List<List<String>> res,
                                    int row,
                                    int[] queens,
                                    Set<Integer> colsPlaced,
                                    Set<Integer> mainDiagonalsPlaced,
                                    Set<Integer> subDiagonalsPlaced) {
        if (row == queens.length) {
            res.add(toChessboard(queens));
            return;
        }

        for (int col = 0; col < queens.length; col++) {
            if (positionValid(row, col, colsPlaced, mainDiagonalsPlaced, subDiagonalsPlaced)) {
                colsPlaced.add(col);
                mainDiagonalsPlaced.add(row - col);
                subDiagonalsPlaced.add(row + col);

                queens[row] = col;

                populateChessboard(res, row + 1, queens, colsPlaced, mainDiagonalsPlaced, subDiagonalsPlaced);

                // revert state
                queens[row] = 0;
                colsPlaced.remove(col);
                mainDiagonalsPlaced.remove(row - col);
                subDiagonalsPlaced.remove(row + col);
            }
        }
    }

    private boolean positionValid(int row,
                                  int col,
                                  Set<Integer> colsPlaced,
                                  Set<Integer> mainDiagonalsPlaced,
                                  Set<Integer> subDiagonalsPlaced) {
        if (colsPlaced.contains(col)) {
            return false;
        }

        if (mainDiagonalsPlaced.contains(row - col)) {
            return false;
        }

        if (subDiagonalsPlaced.contains(row + col)) {
            return false;
        }

        return true;
    }

    private List<String> toChessboard(int[] queens) {
        List<String> board = new ArrayList<>();

        for (int i = 0; i < queens.length; i++) {
            char[] rows = new char[queens.length];
            Arrays.fill(rows, '.');
            rows[queens[i]] = 'Q';
            board.add(new String(rows));
        }

        return board;
    }


}
