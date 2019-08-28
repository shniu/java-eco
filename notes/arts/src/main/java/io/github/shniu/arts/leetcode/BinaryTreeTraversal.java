package io.github.shniu.arts.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树遍历
 *
 * @author shniu
 * @date 2019/08/16 11:33:35
 */
public class BinaryTreeTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorderRecursive(root, result);

        return result;
    }

    // 使用递归的方式实现二叉树中序遍历
    private void inorderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            if (node.left != null) {
                inorderRecursive(node.left, result);
            }

            result.add(node.val);

            if (node.right != null) {
                inorderRecursive(node.right, result);
            }
        }
    }

    // 使用循环+栈的方式
    private List<Integer> inorderLoopAndStack(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        while (curr != null || !stack.isEmpty()) {
            // 左子树不为空就一直把当前节点压栈
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    // 前序递归遍历：根节点 -> 左子树 -> 右子树
    private void preorderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.val);

            if (node.left != null) {
                preorderRecursive(node.left, result);
            }

            if (node.right != null) {
                preorderRecursive(node.right, result);
            }
        }
    }

    private List<Integer> preorderLoop(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode active = root;
        while (active != null || !stack.isEmpty()) {
            if (active == null) {
                active = stack.pop();
            }

            result.add(active.val);
            if (active.right != null) {
                stack.push(active.right);
            }
            active = active.left;
        }

        return result;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
