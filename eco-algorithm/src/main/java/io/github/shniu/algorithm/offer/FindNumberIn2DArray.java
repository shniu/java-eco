package io.github.shniu.algorithm.offer;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * <p>
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 *
 * @author niushaohan
 * @date 2020/7/13 13
 */
public class FindNumberIn2DArray {

    // 思路1：利用二分查找
    // 思路2：逐步缩小范围

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int i = 0;
        int j = col - 1;

        // 从右上逐步缩小范围, 一步步逼近左下
        while (i < row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }
}
