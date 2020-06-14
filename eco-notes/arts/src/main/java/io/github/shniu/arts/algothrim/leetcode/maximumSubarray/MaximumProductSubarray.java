package io.github.shniu.arts.algothrim.leetcode.maximumSubarray;

/**
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 * 152. 乘积最大子序列
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int dpMax = nums[0];
        int dpMin = nums[0];
        int max = nums[0];
        int preMax;
        for (int i = 1; i < nums.length; i++) {
            preMax = dpMax;
            dpMax = Math.max(dpMax * nums[i], Math.max(dpMin * nums[i], nums[i]));
            dpMin = Math.min(preMax * nums[i], Math.min(dpMin * nums[i], nums[i]));
            max = Math.max(max, dpMax);
        }
        return max;
    }
}
