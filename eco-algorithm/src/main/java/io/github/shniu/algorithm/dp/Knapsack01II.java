package io.github.shniu.algorithm.dp;

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
    public int knapsack01(int[] weights, int[] values, int n, int w) {
        // 表示重量为 i 时的最大价值
        int[] dp = new int[w + 1];

        // base case
        for (int i = 0; i <= w; i++) {
            if (weights[i] <= w) {
                dp[i] = values[i];
            }
        }

        // state transfer
        for (int i = 1; i < n; i++) {
            for (int j = w - weights[i]; j >= 0; j--) {
                dp[j + weights[i]] = Math.max(dp[i-1], dp[j] + values[i]);
            }
        }

        // get result
        return dp[w];
    }
}
