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
public class FindLargestValuesInEachTreeRow2 {

    // 本质上是树的层次遍历
    // 实现有两种方式：
    //   1. 使用 BFS: 循环 + 队列
    //   2. 使用 DFS: 递归
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        dfs(root, res, 0);

        return res;
    }

    void dfs(TreeNode node, List<Integer> res, int level) {
        // terminator
        if (node == null) {
            return;
        }

        // process current level
        if (res.size() < level + 1) {
            res.add(node.val);
        }

        if (res.get(level) < node.val) {
            res.set(level, node.val);
        }

        // drill down
        dfs(node.left, res, level + 1);
        dfs(node.right, res, level + 1);
    }
}
