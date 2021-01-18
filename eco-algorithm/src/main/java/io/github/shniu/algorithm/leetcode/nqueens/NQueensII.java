package io.github.shniu.algorithm.leetcode.nqueens;

/**
 * @author niushaohan
 * @date 2021/1/18 12
 */
public class NQueensII {

    public int totalNQueens(int n) {
        return solveNQueens(n, 0, 0, 0, 0);
    }

    /**
     * 计算 N 皇后的解的数量.
     *
     * @param nQueens    n 个皇后
     * @param row        行，从 0 行开始，一直到第 n - 1 行，第 n 行中止
     * @param columns    列，已经放置了皇后的列的位置集合
     * @param diagonals1 主对角线
     * @param diagonals2 副对角线
     * @return 解的数量
     */
    private int solveNQueens(int nQueens, int row, int columns, int diagonals1, int diagonals2) {
        if (row == nQueens) {
            // 说明是一个可用的解
            return 1;
        }

        int count = 0;
        // 1 << n => 2 ^ n
        // (1 << 8) - 1 => 11111111
        int availablePositions = ((1 << nQueens) - 1) & (~(columns | diagonals1 | diagonals2));

        // 0 代表可以放置皇后，1 代表不能放置皇后
        while (availablePositions != 0) {
            //
            int position = availablePositions & (-availablePositions);

            //
            availablePositions = availablePositions & (availablePositions - 1);
            count += solveNQueens(nQueens,
                    row + 1,
                    columns | position,
                    (diagonals1 | position) << 1,
                    (diagonals2 | position) >> 1);
        }

        return count;
    }
}
