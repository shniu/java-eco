package io.github.shniu.arts.algothrim.leetcode.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets/
 * 78. 子集
 * <p>
 * 类似题目还有:
 * <p>
 * 39.组合总和
 * 40. 组合总和 II
 * <p>
 * 46. 全排列
 * 47. 全排列 II
 * <p>
 * 78. 子集
 * 90. 子集 II
 */
public class Subsets {
    // 1. 递归
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        dfs(nums, 0, res, new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, int pos, List<List<Integer>> res, ArrayList<Integer> tempRes) {
        // terminator
        if (pos == nums.length) {
            // System.out.println(tempRes);
            res.add(new ArrayList<>(tempRes));
            return;
        }

        // not pick the number of this position
        dfs(nums, pos + 1, res, tempRes);
        tempRes.add(nums[pos]);
        // pick the number of this position
        dfs(nums, pos + 1, res, tempRes);

        // reverse state: 要清空tempRes，每一层的信息不能相互干扰
        tempRes.remove(tempRes.size() - 1);
    }

    // 2. loop 穷举
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> subres = new ArrayList<>();
            for (List<Integer> sub : res) {
                List<Integer> temp = new ArrayList<>(sub);
                temp.add(nums[i]);
                subres.add(temp);
            }
            res.addAll(subres);
        }

        return res;
    }
}
