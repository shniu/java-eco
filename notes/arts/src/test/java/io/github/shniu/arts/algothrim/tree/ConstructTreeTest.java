package io.github.shniu.arts.algothrim.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ConstructTreeTest {
    private ConstructTree constructTree;
    TreeTraversal treeTraversal;

    @Before
    public void setUp() {
        constructTree = new ConstructTree();
        treeTraversal = new TreeTraversal();
    }

    @Test
    public void fromPreorderAndInorder() {
        TreeNode root = constructTree.fromPreorderAndInorder(new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});
        List<Integer> preorder = treeTraversal.preorder(root);
        System.out.println(preorder);
        List<Integer> inorder = treeTraversal.inorder(root);
        System.out.println(inorder);
        List<Integer> postorder = treeTraversal.postorder(root);
        System.out.println(postorder);
    }

    @Test
    public void fromInorderAndPostorder() {
    }

    @Test
    public void fromPreorderAndPostorder() {
    }
}