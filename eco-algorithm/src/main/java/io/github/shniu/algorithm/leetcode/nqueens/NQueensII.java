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

        // 每次循环都会消去最后的 1，最终 availablePositions 会等于 0，然后会跳出，返回上一层
        while (availablePositions != 0) {
            // 获取每次放置皇后的位置，位运算用来取最后 1 的位置
            int position = availablePositions & (-availablePositions);

            // 每次循环消掉二进制位末尾的 1 个 1，控制循环次数，类似于 for: i = 0; i < n; i++
            availablePositions = availablePositions & (availablePositions - 1);

            // 下钻到下一行，并带入给下一行已经放置了皇后的位置
            // columns | position: 计算列已经放置了皇后的位置
            // (diagonals1 | position) << 1: 计算主对角线方向上受皇后影响的位置
            // (diagonals1 | position) >> 1: 计算副对角线方向上受皇后影响的位置
            count += solveNQueens(nQueens,
                    row + 1,
                    columns | position,
                    (diagonals1 | position) << 1,
                    (diagonals2 | position) >> 1);
        }

        return count;
    }
}
