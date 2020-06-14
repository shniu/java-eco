package io.github.shniu.arts.algothrim.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 构造树
 */
public class ConstructTree {
    private int preIdx = 0;

    // 从前序和中序遍历构造树
    // https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    // 105. 从前序与中序遍历序列构造二叉树
    public TreeNode fromPreorderAndInorder(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null ||
                inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> posMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            posMap.put(inorder[i], i);
        }

        return fromPreorderAndInorder(preorder, posMap, 0, inorder.length);
    }

    private TreeNode fromPreorderAndInorder(
            int[] preorder, Map<Integer, Integer> posMap, int inStart, int inEnd) {
        // terminator
        if (inStart >= inEnd || preIdx >= preorder.length) return null;

        // process current logic
        TreeNode root = new TreeNode(preorder[preIdx]);
        int inRoot = posMap.get(preorder[preIdx]);
        preIdx++;

        // drill down
        root.left = fromPreorderAndInorder(preorder, posMap, inStart, inRoot);
        root.right = fromPreorderAndInorder(preorder, posMap, inRoot + 1, inEnd);
        return root;
    }

    // 关键是 preStart 的位置跟踪
    private TreeNode fromPreorderAndInorder(
            int[] preorder, Map<Integer, Integer> posMap, int inStart, int inEnd, int preStart) {
        if (preStart >= preorder.length || inStart >= inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = posMap.get(root.val);
        int numsLeft = inRoot - inStart;  // 左子树的节点个数，那么右子树的跟节点从 preStart+numsLeft+1 开始

        root.left = fromPreorderAndInorder(preorder, posMap, inStart, inRoot, preStart + 1);
        root.right = fromPreorderAndInorder(preorder, posMap, inRoot + 1, inEnd, preStart + numsLeft + 1);
        return root;
    }

    // 从中序和后序遍历构造树
    // https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    // 106. 从中序与后序遍历序列构造二叉树
    public TreeNode fromInorderAndPostorder(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderPosMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderPosMap.put(inorder[i], i);
        }

        return fromInorderAndPostorder(postorder, inorderPosMap, 0, inorder.length, postorder.length - 1);
    }

    private TreeNode fromInorderAndPostorder(
            int[] postorder, Map<Integer, Integer> inorderPosMap, int inStart, int inEnd, int postStart) {
        if (inStart >= inEnd || postStart < 0) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postStart]);
        int inRoot = inorderPosMap.get(root.val);
        int shiftLeft = inEnd - inRoot;   // 左子树的根节点向左移动的位数

        root.right = fromInorderAndPostorder(postorder, inorderPosMap, inRoot + 1, inEnd, postStart - 1);
        root.left = fromInorderAndPostorder(postorder, inorderPosMap, inStart, inRoot, postStart - shiftLeft);
        return root;
    }

    // 从前序和后序遍历构造树
    // https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
    // 889. 根据前序和后序遍历构造二叉树
    public TreeNode fromPreorderAndPostorder(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postPosMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postPosMap.put(postorder[i], i);
        }
        return fromPreorderAndPostorder(preorder, postPosMap, 0, postorder.length, 0);
    }

    // Todo
    private TreeNode fromPreorderAndPostorder(
            int[] preorder, Map<Integer, Integer> postPosMap, int postStart, int postEnd, int preStart) {
        if (postStart >= postEnd || preStart >= preorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int postRoot = postPosMap.get(root.val);
        int preLeftRoot = postPosMap.get(preorder[preStart + 1]);

        root.left = fromPreorderAndPostorder(preorder, postPosMap, postStart, preLeftRoot + 1, preStart + 1);
        // root.right = fromPreorderAndPostorder(preorder, postPosMap, preLeftRoot + 1, postEnd, preStart + );
        return root;
    }
}
