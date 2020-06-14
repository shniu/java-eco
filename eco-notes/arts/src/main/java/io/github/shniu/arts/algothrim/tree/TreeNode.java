package io.github.shniu.arts.algothrim.tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public boolean hasTwoChildren() {
        return left != null && right != null;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }
}
