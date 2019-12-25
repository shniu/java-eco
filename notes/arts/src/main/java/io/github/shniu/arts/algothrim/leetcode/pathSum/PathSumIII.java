package io.github.shniu.arts.algothrim.leetcode.pathSum;

/**
 * https://leetcode-cn.com/problems/path-sum-iii/
 * 437. 路径总和 III
 */
public class PathSumIII {
    // 1. 递归求解
    public int pathSum1(TreeNode root, int sum) {
        if (root == null) return 0;
        if (root.val > sum) return 0;
        return count(root, sum)
                + pathSum1(root.left, sum)
                + pathSum1(root.right, sum);
    }

    private int count(TreeNode node, int sum) {
        if (node == null) return 0;
        if (node.val > sum) return 0;
        return (node.val == sum ? 1 : 0) +
                count(node.left, sum - node.val) + count(node.right, sum - node.val);
    }

    // 2. 时间优化
    public int pathSum2(TreeNode root, int sum) {
        return pathSum2(root, sum, new int[1000], 0);
    }

    private int pathSum2(TreeNode node, int sum, int[] treeVal, int p) {
        // terminator
        if (node == null) return 0;

        // process current logic
        int tmp = 0, curr = 0;
        treeVal[p] = node.val;
        for (int i = p; i >= 0; i--) {
            tmp += treeVal[i];
            if (tmp == sum) curr++;
        }
        p++;

        // drill down
        int left = pathSum2(node.left, sum, treeVal, p);
        int right = pathSum2(node.right, sum, treeVal, p);

        return curr + left + right;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
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
