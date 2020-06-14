package io.github.shniu.arts.algothrim.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import static org.junit.Assert.*;

public class CommonAncestorTest {

    @Test
    public void findPath() {
        //          100
        //       90    110
        //     10     40
        //   2   8      12

        TreeNode target = new TreeNode(40);
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(90);
        root.left.left = new TreeNode(10);
        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(8);

        root.right = new TreeNode(110);
        root.right.left = target;  // new TreeNode(88);
        root.right.left.right = new TreeNode(12);

        TreeTraversal treeTraversal = new TreeTraversal();
        printII(treeTraversal.inorder(root));

        List<TreeNode> path = new ArrayList<>();
        List<List<TreeNode>> res = new ArrayList<>();
        CommonAncestor commonAncestor = new CommonAncestor();
        commonAncestor.findPath(root, target, path, res);
        print(res.get(0));

        path = new ArrayList<>();
        List<TreeNode> res2 = commonAncestor.findPath2(root, target, path);
        print(res2);

        LongAdder longAdder;

    }

    private void printII(List<Integer> path) {
        for(int node : path) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    private void print(List<TreeNode> path) {
        for(TreeNode node : path) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }
}