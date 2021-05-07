package io.github.shniu.algorithm.dp;

import java.util.Arrays;

/**
 * 升级版的 0-1 背包问题
 * 对于一组不同重量、不同价值、不可分割的物品,我们选择将某些物品装入背包,在满足背包最大重量限制的前提下,
 * 背包中可装入物品的总价值最大是多少呢？背包总的承载重量是 Wkg, 有 n 个物品
 *
 * @author niushaohan
 * @date 2021/4/28 15
 */
public class Knapsack01II {

    /**
     * knapsack
     *
     * @param weights 物品的重量
     * @param values  物品的价值
     * @param n       物品数量
     * @param w       背包总重量
     * @return 最大价值
     */
    public int knapsack01(int[] weights, int[] values, int w, int n) {
        // 表示重量为 i 时的最大价值
        int[] dp = new int[w + 1];

        // state transfer
        for (int i = 1; i <= n; i++) {
            for (int j = w; j >= 1; j--) {
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        return dp[w];
    }

    public int knapsack001(int[] weights, int[] values, int w, int n) {

        int[][] dp = new int[n][w + 1];

        if (weights[0] <= w) {
            dp[0][weights[0]] = values[0];
        }

        for (int i = 1; i < n; i++) {
            // 不放第 i 个物品
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            // 放第 i 个物品
            for (int j = 0; j <= w - weights[i]; j++) {
                dp[i][j + weights[i]] = Math.max(values[i] + dp[i - 1][j], dp[i][j + weights[i]]);
            }
        }

        int maxValue = -1;
        for (int i = 0; i <= w; i++) {
            if (dp[n - 1][i] > maxValue) maxValue = dp[n - 1][i];
        }

        return maxValue;
    }

    public int knapsack002(int[] weights, int[] values, int w, int n) {
        int[][] dp = new int[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j - weights[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - weights[i - 1]] + values[i - 1],
                            dp[i - 1][j]);
                }
            }
        }

        return dp[n][w];
    }
}
