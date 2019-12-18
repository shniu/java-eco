package io.github.shniu.arts.algothrim.leetcode.toBST;

import org.junit.Test;

public class SortedArrayToBSTTest {

    @Test
    public void sortedArrayToBST() {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode root = sortedArrayToBST.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        assert root.val == 0;
        assert root.left.val == -3;
        assert root.right.val == 9;
    }
}