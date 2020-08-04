package io.github.shniu.algorithm.struct.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用前序和中序构造树.
 *
 * @author niushaohan
 * @date 2020/8/4 09
 */
public class PreorderAndInorderTreeBuilder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        // 前序序列：找到 root
        // 中序序列：找到左右子树的分割点
        return fromPreorderAndInorder(preorder, inorder, 0, 0, inorder.length);
    }

    private TreeNode fromPreorderAndInorder(int[] preorder, int[] inorder, int start, int inorderStart, int inorderEnd) {
        // 终止条件
        if (inorderStart >= inorderEnd || start >= preorder.length) {
            return null;
        }

        // 构建根节点
        TreeNode root = new TreeNode(preorder[start]);
        // 从中序序列中找到分割左右子树的位置
        int inorderRoot = 0;
        for (int i = inorderStart; i < inorder.length; i++) {
            if (inorder[i] == preorder[start]) {
                inorderRoot = i;
                break;
            }
        }
        int leave = inorderRoot - inorderStart;

        // 构建左子树
        root.left = fromPreorderAndInorder(preorder, inorder, start + 1, inorderStart, inorderRoot);
        // 构建右子树
        root.right = fromPreorderAndInorder(preorder, inorder, start + leave + 1, inorderRoot + 1, inorderEnd);
        return root;
    }

    /// 优化：inorder 中的 root 节点的查找可以使用 Map 来提高查询性能
    public TreeNode buildTreeOpt1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return fromPreorderAndInorder(preorder, map, 0, 0, inorder.length);
    }

    private TreeNode fromPreorderAndInorder(int[] preorder, Map<Integer, Integer> posMap, int start, int inorderStart, int inorderEnd) {
        // 终止条件
        if (inorderStart >= inorderEnd || start >= preorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[start]);
        Integer inorderRoot = posMap.get(preorder[start]);
        int leave = inorderRoot - inorderStart;

        root.left = fromPreorderAndInorder(preorder, posMap, start + 1, inorderStart, inorderRoot);
        root.right = fromPreorderAndInorder(preorder, posMap, start + leave + 1, inorderRoot + 1, inorderEnd);
        return root;
    }
}
