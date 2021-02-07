package io.github.shniu.algorithm.leetcode.sudoku;

/**
 * @author niushaohan
 * @date 2021/2/5 17
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        return validSudoku(board, 0, 0);
    }

    boolean validSudoku(char[][] board, int i, int j) {
        int m = 9, n = 9;

        if (j == n) {
            return validSudoku(board, i + 1, 0);
        }

        if (i == m) {
            return true;
        }

        if (board[i][j] == '.') {
            return validSudoku(board, i, j + 1);
        }

        char curr = board[i][j];
        for (int k = 0; k < 9; k++) {
            if (k != j && board[i][k] == curr) return false;
            if (k != i && board[k][j] == curr) return false;

            int r = (i / 3) * 3 + k / 3;
            int c = (j / 3) * 3 + k % 3;

            if (r == i && c == j) continue;
            if (board[r][c] == curr) return false;
        }

        return validSudoku(board, i, j + 1);
    }

}
