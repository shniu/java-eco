package io.github.shniu.algorithm.leetcode.tree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/14 09
 */
public class MinimumDistanceBetweenBstNodesTest {

    @Test
    public void test_minDiffInBST() {
        TreeNode root = buildTree();
        MinimumDistanceBetweenBstNodes minimumDistanceBetweenBstNodes = new MinimumDistanceBetweenBstNodes();
        int minDiffInBST = minimumDistanceBetweenBstNodes.minDiffInBST(root);

        assertEquals(1, minDiffInBST);
    }

    /**
     *            90
     *         69     150
     *      49    89
     *        52
     *
     * [56,42,77,23,51,75,90,null,null,null,null,67,null,78,99]
     */
    TreeNode buildTree() {
        TreeNode n69 = new TreeNode(69);
        TreeNode n150 = new TreeNode(150);

        TreeNode n49 = new TreeNode(49);
        TreeNode n89 = new TreeNode(89);
        n69.left = n49;
        n69.right = n89;

        TreeNode n52 = new TreeNode(52);
        n49.right = n52;

        TreeNode root = new TreeNode(90);
        root.left = n69;
        root.right = n150;

        return root;
    }
}