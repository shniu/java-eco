package io.github.shniu.arts.algothrim.leetcode.minimumPathSum;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 64. 最小路径和
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j - 1 < 0) {
                    dp[j] = dp[j] + grid[i][j];
                    continue;
                }
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }

        return dp[n - 1];
    }
}
