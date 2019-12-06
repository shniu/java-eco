package io.github.shniu.arts.algothrim.dp;

/**
 * 0-1 背包问题
 * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 */
public class Knapsack01 {

    // 已经用了回溯法解决了0-1背包问题，主要是使用的递归
    // 现在用 DP 来解决
    public int knapsack01(int[] items, int n, int w) {
        int maxW = 0;
        // dp[i] 表示能否组合出来重量是i的物品
        boolean[] dp = new boolean[w + 1];
        dp[0] = true;
        if (items[0] <= w) {
            dp[items[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) {
                if (dp[j]) {
                    dp[j + items[i]] = true;
                    maxW = Math.max(maxW, j + items[i]);
                }
            }
        }
        return maxW;
    }
}
