package io.github.shniu.algorithm.leetcode.permutations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/2/1 16
 */
public class Permutations2 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(res, nums, used, path);

        return res;
    }

    void backtrack(List<List<Integer>> res, int[] nums, boolean[] used, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);

                backtrack(res, nums, used, path);

                used[i] = false;
                path.removeLast();
            }
        }
    }
}
