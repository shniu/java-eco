package io.github.shniu.algorithm.leetcode.nqueens;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens/
 *
 * @author niushaohan
 * @date 2021/1/17 21
 */
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        // 初始化 chessboard
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }

        // populate chessboard
        populateChessboard(res, chessboard, 0, n);

        return res;
    }

    private void populateChessboard(List<List<String>> res, char[][] chessboard, int row, int nQueens) {
        if (row == nQueens) {
            res.add(dumpSnapshot(chessboard));
            return;
        }

        for (int col = 0; col < nQueens; col++) {
            if (positionValid(chessboard, row, col)) {
                chessboard[row][col] = 'Q';
                populateChessboard(res, chessboard, row + 1, nQueens);
                chessboard[row][col] = '.';
            }
        }
    }

    private List<String> dumpSnapshot(char[][] chessboard) {
        List<String> snapshot = new ArrayList<>();

        for (char[] chars : chessboard) {
            snapshot.add(new String(chars));
        }

        return snapshot;
    }

    private boolean positionValid(char[][] chessboard, int row, int col) {
        // 上方的行有没有占用当前列
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 右上方有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chessboard.length; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 左上方有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
