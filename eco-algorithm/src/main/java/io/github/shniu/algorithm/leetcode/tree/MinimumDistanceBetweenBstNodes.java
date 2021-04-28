package io.github.shniu.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 *
 * @author niushaohan
 * @date 2021/4/14 09
 */
public class MinimumDistanceBetweenBstNodes {

    private int minimumDistance = Integer.MAX_VALUE;
    private List<TreeNode> inorderNodes = new ArrayList<>();

    // 1. 递归的方式，中序遍历的结果是升序的
    public int minDiffInBST2(TreeNode root) {
        helper(root);

        for (int i = 0; i < inorderNodes.size() - 1; i++) {
            minimumDistance = Math.min(minimumDistance, inorderNodes.get(i + 1).val - inorderNodes.get(i).val);
        }
        return minimumDistance;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }

        helper(root.left);
        inorderNodes.add(root);
        helper(root.right);
    }

    // 2. 递归中序遍历，prev 指针配合栈
    public int minDiffInBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        int minimumDistance = Integer.MAX_VALUE;

        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.addFirst(root);
                root = root.left;
            }

            root = stack.removeFirst();

            if (prev != null) {
                minimumDistance = Math.min(minimumDistance, root.val - prev.val);
            }

            prev = root;
            root = root.right;
        }

        return minimumDistance;
    }

}
