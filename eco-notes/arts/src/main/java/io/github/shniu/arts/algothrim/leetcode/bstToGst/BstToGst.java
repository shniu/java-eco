package io.github.shniu.arts.algothrim.leetcode.bstToGst;

public class BstToGst {

    // 利用中序遍历
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private int v = 0;

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.right);
        root.val = root.val + v;
        v = root.val;
        traverse(root.left);
    }

    /// ==== 另一种写法
    public TreeNode bstToGst2(TreeNode root) {
        traverse(root, 0);
        return root;
    }

    private int traverse(TreeNode root, int sum) {
        if (root == null) return sum;
        // right, root
        root.val += traverse(root.right, sum);
        // left
        return traverse(root.left, root.val);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
}
