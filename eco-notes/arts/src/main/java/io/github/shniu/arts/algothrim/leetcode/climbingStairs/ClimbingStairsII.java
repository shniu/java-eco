package io.github.shniu.arts.algothrim.leetcode.climbingStairs;

/**
 * 拓展题目
 * <p>
 * 假设你正在爬楼梯，每次你可以爬 1 个、2 个或 3 个台阶，那么在相邻步数不能相同的条件下，你有多少种不同的方法可以爬到第 n 阶呢？
 * 分析：https://shimo.im/docs/pXr9YwjPgpxdHrRT/read
 */
public class ClimbingStairsII {

    // dp
    //   climb 1 stair
    public int climbStairs(int n) {
        if (n <= 2) return n;
        if (n == 3) return 3;

        // state array
        int[][] dp = new int[4][n + 1];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[1][3] = dp[2][3] = dp[3][3] = 1;

        for (int i = 4; i <= n; i++) {
            // dp 状态转移
            dp[1][i] = dp[2][i - 1] + dp[3][i - 1];
            dp[2][i] = dp[1][i - 2] + dp[3][i - 2];
            dp[3][i] = dp[1][i - 3] + dp[2][i - 3];
        }
        return dp[1][n] + dp[2][n] + dp[3][n];
    }

}
