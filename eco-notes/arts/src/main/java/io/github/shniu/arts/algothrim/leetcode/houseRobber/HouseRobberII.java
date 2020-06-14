package io.github.shniu.arts.algothrim.leetcode.houseRobber;

/**
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 * 213. 打家劫舍 II
 */
public class HouseRobberII {
    // dp
    // 难点在于收尾相接，也就是说偷第一个不能偷最后一个，偷最后一个不能偷第一个
    // 所以可以转换成求两个问题的最大值
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        return Math.max(rob(nums, 0, n - 2),
                rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int s, int e) {
        int prev = 0, curr = nums[s], temp;
        for (int i = s + 1; i <= e; i++) {
            temp = curr;
            curr = Math.max(curr, prev + nums[i]);
            prev = temp;
        }
        return curr;
    }
}
