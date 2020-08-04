package io.github.shniu.algorithm.offer;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 *
 * @author niushaohan
 * @date 2020/7/17 00
 */
public class BuildTree {

    /**
     * 实现请移步 {@link io.github.shniu.algorithm.struct.tree.PreorderAndInorderTreeBuilder}
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return new TreeNode(1);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
