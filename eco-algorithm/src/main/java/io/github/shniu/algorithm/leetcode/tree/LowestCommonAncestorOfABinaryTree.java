package io.github.shniu.algorithm.leetcode.tree;

/**
 * 236. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author niushaohan
 * @date 2021/1/28 17
 */
public class LowestCommonAncestorOfABinaryTree {

    // 1. 暴力的办法，求出 p 的路径，再求出 q 的路径，把两个路径从 root 依次往下比较
    // 2. DFS
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

}
