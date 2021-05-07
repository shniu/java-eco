package io.github.shniu.algorithm.dp;

/**
 * 完全背包问题
 * 有N种物品和一个容量为W的背包，每种物品都有无限件可用。第i种物品的容量是c[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 *
 * @author niushaohan
 * @date 2021/5/6 15
 */
public class CompleteKnapsackII {
    /**
     * 完全背包
     *
     * @param weights 物品容量
     * @param values  物品价格
     * @param n       物品数量
     * @param w       背包容量
     * @return 最大价格
     */
    public int knapsack(int[] weights, int[] values, int w, int n) {
        if (weights == null || values == null) return 0;

        int[] dp = new int[w + 1];
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j - weights[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
        }

        return dp[w];
    }
}
