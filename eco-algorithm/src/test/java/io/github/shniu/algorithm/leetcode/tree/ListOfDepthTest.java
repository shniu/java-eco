package io.github.shniu.algorithm.leetcode.tree;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/3/17 18
 */
public class ListOfDepthTest {

    @Test
    public void test_listOfDepth() {
        // given
        TreeNode tree = buildTree();

        // when
        ListOfDepth listOfDepth = new ListOfDepth();
        ListNode[] listNodes = listOfDepth.listOfDepth(tree);
        // ListNode[] listNodes = listOfDepth.listOfDepth2(tree);

        for (ListNode listNode : listNodes) {
            ListNode n = listNode;
            while (n != null) {
                System.out.print(n + " ");
                n = n.next;
            }
            System.out.println();
        }
    }

    /**
     *          1
     *        3   2
     *      5  4    9
     */
    TreeNode buildTree() {
        TreeNode five = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        TreeNode nine = new TreeNode(9);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3, five, four);
        root.right = new TreeNode(2, null, nine);

        return root;
    }
}