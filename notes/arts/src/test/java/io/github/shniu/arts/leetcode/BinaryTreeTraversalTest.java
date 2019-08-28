package io.github.shniu.arts.leetcode;

import org.junit.Test;

import java.util.List;

public class BinaryTreeTraversalTest {

    @Test
    public void test_inorderTraversal() {
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();
        List<Integer> nodes = traversal.inorderTraversal(null);
        System.out.println(nodes);
    }
}