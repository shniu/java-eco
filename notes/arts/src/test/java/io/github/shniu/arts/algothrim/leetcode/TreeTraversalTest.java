package io.github.shniu.arts.algothrim.leetcode;

import io.github.shniu.arts.algothrim.tree.TreeTraversal;
import org.junit.Test;

import java.util.List;

public class TreeTraversalTest {

    @Test
    public void test_inorderTraversal() {
        TreeTraversal traversal = new TreeTraversal();
        List<Integer> nodes = traversal.inorder(null);
        System.out.println(nodes);
    }
}