package io.github.shniu.algorithm.leetcode.sudoku;

/**
 * @author niushaohan
 * @date 2021/2/5 17
 */
public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        // 行出现的数
        // 列出现的数
        // 9 个小方格出现的数：根据 (i,j) 位置定位在哪个小方格，然后判断是否出现了这个数字
        print(board);

        backtrack(board, 0, 0);

        print(board);
    }

    private boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            // print(board);
            return backtrack(board, i + 1, 0);
        }

        if (i == m) {
            return true;
        }

        if (board[i][j] != '.') {
            return backtrack(board, i, j + 1);
        }

        for (char k = '1'; k <= '9'; k++) {
            if (valid(board, i, j, k)) {
                board[i][j] = k;
                if (backtrack(board, i, j + 1)) {
                    return true;
                }
                board[i][j] = '.';
            }
        }

        return false;
    }

    boolean valid(char[][] board, int i, int j, char k) {
        for (int l = 0; l < 9; l++) {
            if (board[i][l] == k) return false;
            if (board[l][j] == k) return false;

            // 计算在小方格的位置
            // row: (i / 3) * 3 + l / 3
            // col: (j / 3) * 3 + l % 3
            if (board[(i / 3) * 3 + l / 3][(j / 3) * 3 + l % 3] == k) return false;
        }

        return true;
    }

    void print(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append("'").append(board[i][j]).append("', ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
