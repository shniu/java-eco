package io.github.shniu.algorithm.dp;

/**
 * @author niushaohan
 * @date 2021/5/4 11
 */
public class Stairs {

    // 每次可以爬 1 步或 2 步, DP 解法
    public int climbStairsWithOneOrTwoStep(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // 每次可以爬 1 步或 2 步, DP 解法优化空间: 状态空间压缩
    public int climbStairsWithOneOrTwoStepOptSpace(int n) {
        int prev_1 = 1;
        int prev_2 = 2;
        int res = 0;

        for (int i = 3; i <= n; i++) {
            res = prev_1 + prev_2;
            prev_1 = prev_2;
            prev_2 = res;
        }

        return res;
    }

    // 每次可以爬 1 步或 2 步, 递归+备忘录解法
    public int climbStairsWithOneOrTwoStepRecurOpt(int n) {
        int[] memo = new int[n + 1];
        return helper(n, memo);
    }

    int helper(int n, int[] memo) {
        if (n <= 2) return n;
        if (memo[n] > 0)  return memo[n];

        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);

        return memo[n];
    }

    /// ---
    // 可以爬 1 或 2 或 3个台阶，且相邻两步不能相同
    public int climbStairs3(int n) {
        int[][] dp = new int[n + 1][4];

        // dp[i][j]
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= n; i++) {
            dp[i][1] = dp[i - 1][2] + dp[i - 1][3];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][3];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2];
        }

        return dp[n][1] + dp[n][2] + dp[n][3];
    }
}
