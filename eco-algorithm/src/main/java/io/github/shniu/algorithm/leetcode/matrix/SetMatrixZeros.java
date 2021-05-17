package io.github.shniu.algorithm.leetcode.matrix;

/**
 * 73 https://leetcode-cn.com/problems/set-matrix-zeroes/
 * @author niushaohan
 * @date 2021/5/8 15
 */
public class SetMatrixZeros {

    // 投影到第一行和第一列上
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean row0ZeroFlag = false, col0ZeroFlag = false;

        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                row0ZeroFlag = true;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0ZeroFlag = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (row0ZeroFlag) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if (col0ZeroFlag) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}
