package io.github.shniu.arts.algorithm;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeTraversalTest {

    @Test
    public void test_inorderTraversal() {
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();
        List<Integer> nodes = traversal.inorderTraversal(null);
        System.out.println(nodes);
    }
}