package io.github.shniu.arts.algothrim.leetcode.toBST;

import io.github.shniu.arts.algothrim.tree.BST;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BSTTest {
    private BST bst;

    @Before
    public void setUp() {
        bst = new BST();
    }

    @Test
    public void test_whenFindEmptyTree_thenReturnNull() {
        BST.Node node = bst.find(10);
        Assert.assertNull(node);
    }

    @Test
    public void test_givenTheValueContainedInTree_thenReturnTheNode() {
        int[] eles = new int[]{100, 105, 10, 20, 15, 30, 25, 40, 26, 101};
        for (int ele : eles) {
            bst.insert(ele);
        }

        BST.Node notFound = bst.find(1);
        BST.Node found = bst.find(20);
        Assert.assertNull(notFound);
        Assert.assertEquals(20, found.getVal());

        bst.delete(22);
        bst.delete(20);
        BST.Node delNode1 = bst.find(20);
        Assert.assertNull(delNode1);

        bst.delete(100);
        BST.Node delNode2 = bst.find(100);
        Assert.assertNull(delNode2);

        List<Integer> inorder = bst.inorderTraversal();
        System.out.println(inorder);

        List<Integer> preorder = bst.preorderTraversal();
        System.out.println(preorder);

        List<Integer> postorder = bst.postorderTraversal();
        System.out.println(postorder);

        BST.Node min = bst.min();
        Assert.assertEquals(10, min.getVal());
        BST.Node max = bst.max();
        Assert.assertEquals(105, max.getVal());

        System.out.printf("Tree height: %d", bst.height());
    }
}