package io.github.shniu.arts.algothrim.leetcode.toBST;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * 108. 将有序数组转换为二叉搜索树
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    // 递归实现
    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }

        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}
