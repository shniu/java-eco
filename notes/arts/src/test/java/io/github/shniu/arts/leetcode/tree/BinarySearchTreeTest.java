package io.github.shniu.arts.leetcode.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @Before
    public void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    public void test_whenFindEmptyTree_thenReturnNull() {
        BinarySearchTree.Node node = bst.find(10);
        Assert.assertNull(node);
    }

    @Test
    public void test_givenTheValueContainedInTree_thenReturnTheNode() {
        bst.insert(100);
        bst.insert(105);
        bst.insert(10);
        bst.insert(20);

        BinarySearchTree.Node notFound = bst.find(1);
        BinarySearchTree.Node found = bst.find(20);
        Assert.assertNull(notFound);
        Assert.assertEquals(20, found.getVal());
    }
}