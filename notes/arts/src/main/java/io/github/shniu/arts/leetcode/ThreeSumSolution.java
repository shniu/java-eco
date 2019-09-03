package io.github.shniu.arts.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * three sum
 * https://leetcode-cn.com/problems/3sum
 */
public class ThreeSumSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return res;
        }

        // 对数组排序
        Arrays.sort(nums);

        if (nums[0] > 0 || nums[nums.length - 1] < 0) return res;

        // 从左到右找
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字大于0，三数之和必然大于0
            if (nums[i] > 0) break;

            // 会有重复结果，跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
            }
        }

        return res;
    }
}
