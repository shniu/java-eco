package io.github.shniu.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
 *
 * @author niushaohan
 * @date 2021/3/17 18
 */
public class PrintLevelOrder {

    // 层次遍历
    // 递归
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversing(root, 0, res);
        return res;
    }

    void traversing(TreeNode node, int level, List<List<Integer>> res) {
        if (node == null) {
            return;
        }

        if (res.size() <= level) {
            List<Integer> entry = new ArrayList<>();
            entry.add(node.val);
            res.add(entry);
        } else {
            List<Integer> entry = res.get(level);
            entry.add(node.val);
        }

        traversing(node.left, level + 1, res);
        traversing(node.right, level + 1, res);
    }
}
