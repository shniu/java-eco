package io.github.shniu.algorithm.struct.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author niushaohan
 * @date 2020/8/4 09
 */
public class PreorderAndInorderTreeBuilderTest {
    private PreorderAndInorderTreeBuilder treeBuilder;

    @Before
    public void setUp() throws Exception {
        treeBuilder = new PreorderAndInorderTreeBuilder();
    }

    @Test
    public void test_nullInput_getNull() {
        // given
        TreeNode treeNode = treeBuilder.buildTree(null, null);

        // then
        assertNull(treeNode);
    }

    @Test
    public void test_buildTree() {
        // given
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        // when
        TreeNode treeNode = treeBuilder.buildTree(preorder, inorder);

        // then
        assertEquals(treeNode.val, 3);

        assertEquals(treeNode.left.val, 9);
        assertEquals(treeNode.right.val, 20);
    }

    @Test
    public void test_buildTree2() {
        // given
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        // when
        TreeNode treeNode = treeBuilder.buildTree(preorder, inorder);

        // then
        assertEquals(treeNode.val, 3);

        assertEquals(treeNode.left.val, 9);
        assertEquals(treeNode.right.val, 20);
    }
}