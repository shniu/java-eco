package io.github.shniu.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/4/29 15
 */
public class BackTraverse {
    private List<TreeNode> res = new ArrayList<>();

    public void traverse(TreeNode root) {
        if (root == null) return;

        traverse(root.right);
        res.add(root);
        if (res.size() == 2) {
            return;
        } else {
            traverse(root.left);
        }
    }

    public void print() {
        for (TreeNode node : res) {
            System.out.println(node.val);
        }
    }

    public static void main(String[] args) {
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode nine = new TreeNode(9);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4, five, three);
        root.right = new TreeNode(2, null, nine);

        BackTraverse backTraverse = new BackTraverse();
        backTraverse.traverse(root);

        backTraverse.print();
    }
}
