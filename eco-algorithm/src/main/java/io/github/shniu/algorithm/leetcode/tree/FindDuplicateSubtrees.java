package io.github.shniu.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author niushaohan
 * @date 2021/4/29 00
 */
public class FindDuplicateSubtrees {

    private Map<String, Integer> memo = new HashMap<>();
    private List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        find(root);
        return res;
    }

    private String find(TreeNode root) {
        if (root == null) {
            return "@";
        }

        String left = find(root.left);
        String right = find(root.right);

        String subTree = left + ";" + right + ";" + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        if (freq == 1) {
            res.add(root);
        }

        memo.put(subTree, freq + 1);
        return subTree;
    }
}
