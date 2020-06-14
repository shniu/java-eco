package io.github.shniu.arts.algothrim.leetcode.houseRobber;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/house-robber/
 * 198. 打家劫舍
 */
public class HouseRobber {
    // 1. dp
    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // dp[i][0] 表示不偷第i个房子的最大值
        // dp[i][1] 表示偷第i个房子的最大值
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // dp 优化
    public int rob12(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // dp[i] 表示到第i个房子必偷时的最大值
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    // dp 优化
    public int rob13(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int prev = 0, temp, curr = nums[0];

        for (int i = 2; i <= n; i++) {
            temp = curr;
            curr = Math.max(curr, prev + nums[i - 1]);
            prev = temp;
        }
        return curr;
    }

    // 2. 递归
    public int rob2(int[] nums) {
        return rob(nums, nums.length - 1);
    }

    private int rob(int[] nums, int n) {
        // terminator
        if (n == 0) return nums[0];
        if (n < 0) return 0;

        // drill down
        // 偷第n个房子
        int rob = rob(nums, n - 2) + nums[n];
        // 不偷第n个房子
        int notRob = rob(nums, n - 1);

        return Math.max(rob, notRob);
    }

    // 添加memo数组
    public int rob22(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return rob(nums, nums.length - 1, memo);
    }

    private int rob(int[] nums, int n, int[] memo) {
        // terminator
        if (n == 0) return nums[0];
        if (n < 0) return 0;

        if (memo[n] != -1) return memo[n];

        // drill down
        // 偷第n个房子
        int rob = rob(nums, n - 2, memo) + nums[n];
        // 不偷第n个房子
        int notRob = rob(nums, n - 1, memo);

        memo[n] = Math.max(rob, notRob);
        return memo[n];
    }
}
