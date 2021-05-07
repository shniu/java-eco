package io.github.shniu.algorithm.dp;

/**
 * 0-1 背包问题
 * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 *
 * @author niushaohan
 * @date 2021/4/28 13
 */
public class Knapsack01 {

    public int knapsack01(int[] items, int n, int w) {
        // dp table
        boolean[] dp = new boolean[w + 1];

        // base case
        dp[0] = true;
        dp[items[0]] = items[0] <= w;

        for (int i = 0; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) {
                if (dp[j]) {
                    dp[j + items[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; i++) {
            if (dp[i]) return i;
        }

        return 0;
    }

    public int knapsack2(int[] weights, int n, int w) {
        // 使用二维数组存储状态
        // dp[][] 表示到前 i 个物品是否能满足最大重量为 j 的情况
        //  dp[1][3] = true 表示 0 和 1 两个物品的组合可以满足重量是 3
        //  dp[2][3] 肯定也是 true 的
        // 当计算 dp[3][6] 且 index=3 物品重量是 3，如果放如这个物品，就需要前 i-1 个物品满足 dp[2][3]=true
        boolean[][] dp = new boolean[n][w + 1];

        // base case
        dp[0][0] = true;
        dp[0][weights[0]] = weights[0] <= w; // 第一个物品特殊处理

        // 剩下的物品
        for (int i = 1; i < n; i++) {
            // 不放第 i 个物品
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            // 放第 i 个物品
            for (int j = 0; j <= w - weights[i]; j++) {
                dp[i][j + weights[i]] = dp[i - 1][j];
            }
        }

        for (int i = w; i >= 0; i++) {
            if (dp[n - 1][i]) return i;
        }

        return 0;
    }
}
