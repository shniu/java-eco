package io.github.shniu.algorithm.leetcode.tree;

import java.util.LinkedList;

/**
 * @author niushaohan
 * @date 2021/4/28 21
 */
public class FlattenBinaryTreeToLinkedList {

    // 后序遍历
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // flatten 左子树
        flatten(root.left);
        // flatten 右子树
        flatten(root.right);

        // 左子树和右子树已经形成链表，需要处理左子树和右子树的位置

        // 暂存左右子树的指针
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 左子树移动到右子树，左子树置为空
        root.left = null;
        root.right = left;

        // 找到右子树可以追加的节点，应该是原来左子树最右边的叶子节点
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        // 把右子树放在原来左子树最右边的节点的右边
        p.right = right;
    }

    // 前序遍历 + 前向指针实现：不是很好想到
    // 遍历的过程中：如果 prev 不为空，说明curr已经做了移动，要考虑移动左节点到右节点，左节点置空
    //   右子树放在 stack 中，prev 指向 curr，当前节点移向左节点
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode prev = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr == null) {
                curr = stack.removeFirst();
            }

            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }

            if (curr.right != null) {
                stack.addFirst(curr.right);
            }

            prev = curr;
            curr = curr.left;
        }
    }
}
