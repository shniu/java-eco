package io.github.shniu.arts.leetcode.maximumSubarray;

/**
 * https://leetcode-cn.com/problems/maximum-subarray
 * 53. 最大子序和
 */
public class MaximumSubarray {
    // dp
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int dp = nums[0];
        for (int num : nums) {
            // 状态转移方程：前i-1的最大序列和与第i个数（取或者不取）比较最大值
            dp = Math.max(dp, 0) + num;
            res = Math.max(dp, res);
        }
        return res;
    }
}
