package io.github.shniu.algorithm.leetcode.tree;

import java.util.LinkedList;

/**
 * 230 https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 *
 * @author niushaohan
 * @date 2021/5/12 23
 */
public class KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return val;
    }

    private int i = 0;
    private int val = 0;

    void inorder(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        inorder(root.left, k);
        i++;
        if (i == k) {
            val = root.val;
            return;
        }
        inorder(root.right, k);
    }

    public int kthSmallestLoop(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        // stack.addFirst(root);

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // 左节点不是 null 一直往下找，并把自己入栈
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }

            // 访问当前节点
            curr = stack.removeFirst();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }

        return -1;
    }
}
