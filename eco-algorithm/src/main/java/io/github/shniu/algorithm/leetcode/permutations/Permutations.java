package io.github.shniu.algorithm.leetcode.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/submissions/
 *
 * @author niushaohan
 * @date 2021/2/1 14
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, nums, 0);
        return res;
    }

    void printIdent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("    ");
        }
    }

    void backtrack(List<List<Integer>> res, int[] nums, int level) {
        // terminator
        if (nums.length == level) {
            List<Integer> r = new ArrayList<>();
            for (int num : nums) {
                r.add(num);
            }
            res.add(r);
            printIdent(level);
            System.out.println("Got a result => " + r + ", nums = " + Arrays.toString(nums) + ", level = " + level);
            return;
        }

        // current level
        for (int i = level; i < nums.length; i++) {
            printIdent(level);
            System.out.println("Enter => i = " + i + ", nums[i] = "
                    + nums[i] + ", nums = " + Arrays.toString(nums) + ", level = " + level + ", nums[level] = " + nums[level]);

            int tmp = nums[level];
            nums[level] = nums[i];
            nums[i] = tmp;

            printIdent(level);
            System.out.println("After Exchange => i = " + i + ", nums[i] = "
                    + nums[i] + ", nums = " + Arrays.toString(nums) + ", level = " + level + ", nums[level] = " + nums[level]);

            backtrack(res, nums, level + 1);

            printIdent(level);
            System.out.println("Before backtrack => i = " + i + ", nums[i] = "
                    + nums[i] + ", nums = " + Arrays.toString(nums) + ", level = " + level + ", nums[level] = " + nums[level]);

            tmp = nums[level];
            nums[level] = nums[i];
            nums[i] = tmp;

            printIdent(level);
            System.out.println("After backtrack => i = " + i + ", nums[i] = "
                    + nums[i] + ", nums = " + Arrays.toString(nums) + ", level = " + level + ", nums[level] = " + nums[level]);
        }
    }
}
