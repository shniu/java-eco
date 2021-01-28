package io.github.shniu.algorithm.leetcode.tree;

/**
 * 235. 二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * @author niushaohan
 * @date 2021/1/28 17
 */
public class LowestCommonAncestorOfABinarySearchTree2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0) {
            return root;
        }

        return lowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);
    }

}
