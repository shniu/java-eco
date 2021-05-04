package io.github.shniu.arts.algothrim.leetcode.bestTimeToBuyAndSellStock;

/**
 * @author niushaohan
 * @date 2021/4/30 14
 */
public class BestTimeToStockWithTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        int[][] dp = new int[n][2];

        // base case
        dp[0][0] = 0;
        dp[0][1] = -(prices[0] + fee);

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }

        return dp[n - 1][0];
    }

    // 简化
    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;

        int dp_0 = 0, dp_1 = -(prices[0] + fee);

        for (int i = 1; i < n; i++) {
            int tmp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + prices[i]);
            dp_1 = Math.max(dp_1, tmp - prices[i] - fee);
        }

        return dp_0;
    }
}
