package io.github.shniu.algorithm.leetcode.tree;

import org.junit.Test;

/**
 * @author niushaohan
 * @date 2021/5/12 23
 */
public class KthSmallestInBSTTest {

    @Test
    public void kthSmallest() {
        KthSmallestInBST kthSmallestInBST = new KthSmallestInBST();
        int i = kthSmallestInBST.kthSmallest(buildTree2(), 2);
        assert i == 2;

        int nums = kthSmallestInBST.kthSmallestLoop(buildTree2(), 3);
        System.out.println(nums);
    }

    TreeNode buildTree2() {
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        two.left = new TreeNode(1);

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3, two, four);
        root.right = new TreeNode(6, null, null);

        return root;
    }
}