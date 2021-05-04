package io.github.shniu.algorithm.leetcode.tree;

import java.util.Map;

/**
 * @author niushaohan
 * @date 2021/4/29 00
 */
public class MaximumSumBstInBinary {

    private int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    private int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[] {1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = traverse(root.left);
        int[] right = traverse(root.right);

        int[] res = new int[4];
        if (left[0] == 1 && right[0] == 1 && left[2] < root.val && right[1] > root.val) {
            res[0] = 1;
            res[1] = Math.min(left[1], root.val);
            res[2] = Math.max(root.val, right[2]);
            res[3] = left[3] + right[3] + root.val;
            maxSum = Math.max(maxSum, res[3]);
        } else {
            res[0] = 0;
        }

        return res;
    }

}
