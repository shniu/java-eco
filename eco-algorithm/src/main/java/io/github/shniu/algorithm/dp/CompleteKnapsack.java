package io.github.shniu.algorithm.dp;

/**
 * 有一个背包，最大容量为 W，有一系列物品 weights，每个物品的重量为 weights[i]，
 * 每个物品的数量无限。请问有多少种方法，能够把背包恰好装满？
 *
 * @author niushaohan
 * @date 2021/5/6 15
 */
public class CompleteKnapsack {

    /**
     * 完全背包
     *
     * @param weights 物品容量
     * @param n       物品数量
     * @param w       背包容量
     * @return 最大价格
     */
    public int knapsack(int[] weights, int n, int w) {
        if (weights == null) return 0;

        int[] dp = new int[w + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                if (i - weights[i] >= 0) {
                    dp[j] = dp[j] + dp[j - weights[i]];
                }
            }
        }

        return dp[w];
    }

}
