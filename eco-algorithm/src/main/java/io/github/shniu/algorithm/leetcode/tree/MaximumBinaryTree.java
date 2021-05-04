package io.github.shniu.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 *
 * @author niushaohan
 * @date 2021/3/17 19
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }

        int maxIndex = low, maxVal = nums[low];
        for (int i = low; i <= high; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(maxVal);
        root.left = buildTree(nums, low, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, high);
        return root;
    }
}
