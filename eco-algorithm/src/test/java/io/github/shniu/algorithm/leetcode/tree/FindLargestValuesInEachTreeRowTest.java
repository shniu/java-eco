package io.github.shniu.algorithm.leetcode.tree;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/1/28 16
 */
public class FindLargestValuesInEachTreeRowTest {

    @Test
    public void test_largestValues() {
        // given
        FindLargestValuesInEachTreeRow findLargestValuesInEachTreeRow = new FindLargestValuesInEachTreeRow();

        // when
        List<Integer> values = findLargestValuesInEachTreeRow.largestValues(buildTree());

        // then
        assertEquals(2, values.size());
        assertEquals(99, (long) values.get(0));
        assertEquals(30, (long) values.get(1));
    }

    @Test
    public void test_largestValues_null() {
        // given
        FindLargestValuesInEachTreeRow findLargestValuesInEachTreeRow = new FindLargestValuesInEachTreeRow();

        // when
        List<Integer> values = findLargestValuesInEachTreeRow.largestValues(null);

        // then
        assertEquals(0, values.size());
    }

    @Test
    public void test_largestValues_multiNodes() {
        // given
        FindLargestValuesInEachTreeRow findLargestValuesInEachTreeRow = new FindLargestValuesInEachTreeRow();

        // when
        List<Integer> values = findLargestValuesInEachTreeRow.largestValues(buildTree2());

        // then
        System.out.println(values);
    }

    TreeNode buildTree() {
        TreeNode root = new TreeNode(99);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);

        return root;
    }

    TreeNode buildTree2() {
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode nine = new TreeNode(9);

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3, five, three);
        root.right = new TreeNode(2, null, nine);

        return root;
    }
}