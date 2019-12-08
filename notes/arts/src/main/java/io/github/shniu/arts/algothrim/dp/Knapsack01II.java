package io.github.shniu.arts.algothrim.dp;

import java.util.Arrays;

/**
 * 升级版的 0-1 背包问题
 * 对于一组不同重量、不同价值、不可分割的物品,我们选择将某些物品装入背包,在满足背包最大重量限制的前提下,
 * 背包中可装入物品的总价值最大是多少呢？背包总的承载重量是 Wkg, 有 n 个物品
 */
public class Knapsack01II {
    private int maxV;

    // dp
    public int knapsack01(int[] items, int[] value, int n, int w) {
        // dp[i][cw] 表示前i个物品放入重量为cw背包中可以获得的最大价值
        // dp[i][cw] = max(dp[i-1][cw], dp[i-1][cw-items[i]] + value[i])
        int[][] dp = new int[n][w + 1];

        // base case
        for (int i = 0; i <= w; i++) {
            if (items[0] >= w) dp[0][i] = value[i];
        }

        // dp
        for (int i = 1; i < n; i++) {
            for (int j = items[i]; j <= w; j++) {
                dp[i][j] = Math.max(dp[i - 1][j],
                        dp[i - 1][j - items[i]] + value[i]);
            }
        }

        return dp[n - 1][w];
    }

    // dp 空间优化
    public int knapsack01Plus(int[] items, int[] value, int n, int w) {
        // dp[cw] 表示前i个物品放入重量为cw背包中可以获得的最大价值
        // dp[cw] = max(dp[cw], dp[cw-items[i]] + value[i])
        int[] dp = new int[w + 1];

        // base case
        for (int i = 0; i <= w; i++) {
            if (items[0] >= w) dp[i] = value[i];
        }

        // dp
        for (int i = 1; i < n; i++) {
            for (int j = items[i]; j <= w; j++) {
                dp[j] = Math.max(dp[j],
                        dp[j - items[i]] + value[i]);
            }
        }

        return dp[w];
    }

    // backtrack, 返回最大价值
    public int backtrack(int[] items, int[] value, int n, int w) {
        find(0, 0, 0, items, value, n, w);
        return maxV;
    }

    private void find(int i, int cw, int cv, int[] items, int[] value, int n, int w) {
        if (i == n || cw == w) {
            maxV = Math.max(maxV, cv);
            return;
        }

        // 不放
        find(i + 1, cw, cv, items, value, n, w);
        if (items[i] + cw <= w) {
            // 放
            find(i + 1, cw + items[i], cv + value[i], items, value, n, w);
        }
    }

    public static void main(String[] args) {
        Knapsack01II knapsack01II = new Knapsack01II();
        int[] items = new int[]{2, 2, 4, 6, 3}; // 物品的重量
        int[] value = new int[]{3, 4, 8, 9, 6}; // 物品的价值

        int maxV = knapsack01II.backtrack(items, value, 5, 9);
        System.out.println(maxV);

        // dp
        maxV = knapsack01II.knapsack01(items, value, 5, 9);
        System.out.println(maxV);

        maxV = knapsack01II.knapsack01Plus(items, value, 5, 9);
        System.out.println(maxV);

        // 正确结果是 18
    }

}
