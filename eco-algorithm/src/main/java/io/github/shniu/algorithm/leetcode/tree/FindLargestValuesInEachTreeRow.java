package io.github.shniu.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 515. 在每个树行中找最大值
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 *
 * @author niushaohan
 * @date 2021/1/28 16
 */
public class FindLargestValuesInEachTreeRow {

    // 本质上是树的层次遍历
    // 实现有两种方式：
    //   1. 使用 BFS: 循环 + 队列
    //   2. 使用 DFS: 递归
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();

            int largestValue = Integer.MIN_VALUE;
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode current = queue.removeFirst();

                if (current == null) {
                    break;
                }

                if (largestValue < current.val) {
                    largestValue = current.val;
                }

                if (current.left != null) {
                    queue.addLast(current.left);
                }

                if (current.right != null) {
                    queue.addLast(current.right);
                }
            }

            res.add(largestValue);
        }

        return res;
    }

    // 递归
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        search(root, 0, res);
        return res;
    }

    void search(TreeNode node, int level, List<Integer> res) {
        if (node == null) {
            return;
        }

        if (res.size() <= level) {
            res.add(node.val);
        } else {
            res.set(level, Math.max(node.val, res.get(level)));
        }

        search(node.left, level + 1, res);
        search(node.right, level + 1, res);
    }
}
