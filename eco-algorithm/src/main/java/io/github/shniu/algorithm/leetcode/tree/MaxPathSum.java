package io.github.shniu.algorithm.leetcode.tree;

/**
 * @author niushaohan
 * @date 2021/4/12 17
 */
public class MaxPathSum {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        nodeMaxGain(root);
        return maxSum;
    }

    private int nodeMaxGain(TreeNode root) {
        // 如果空节点，没有增益，计 0
        if (root == null) {
            return 0;
        }

        // 左子树的最大增益，如果左子树增益是负的，直接舍弃掉
        int maxLeftGain = Math.max(nodeMaxGain(root.left), 0);
        // 右子树的最大增益，如果右子树增益是负的，直接舍弃掉
        int maxRightGain = Math.max(nodeMaxGain(root.right), 0);

        // root 节点在路径中的最大值，左子树->根->右子树 这么一条路径的最大和
        int newPathGain = maxLeftGain + maxRightGain + root.val;

        // 和当前最大值进行比较，尝试更新
        maxSum = Math.max(newPathGain, maxSum);

        // 返回上一级，要么是根+左子树，要么是根+右子树
        // 返回到上一级，如果是个负数，说明增益是负的，肯定不是最大值的路径
        return root.val + Math.max(maxLeftGain, maxRightGain);
    }
}
