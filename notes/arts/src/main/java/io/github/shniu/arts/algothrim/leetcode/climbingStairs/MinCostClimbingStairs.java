package io.github.shniu.arts.algothrim.leetcode.climbingStairs;

/**
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 * 746. 使用最小花费爬楼梯
 */
public class MinCostClimbingStairs {
    // dp
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;

        // dp[i] 表示到达i阶楼梯时的最小花费
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int prev2 = cost[0];
        int prev1 = cost[1];
        int minCost = Math.min(prev2, prev1);
        for (int i = 2; i < n; i++) {
            minCost = Math.min(prev2 + cost[i], prev1 + cost[i]);
            prev2 = prev1;
            prev1 = minCost;
        }
        return minCost;
    }
}
