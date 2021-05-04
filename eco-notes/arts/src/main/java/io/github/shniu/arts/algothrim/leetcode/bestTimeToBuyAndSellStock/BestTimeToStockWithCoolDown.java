package io.github.shniu.arts.algothrim.leetcode.bestTimeToBuyAndSellStock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 123. 买卖股票的最佳时机 III
 */
public class BestTimeToStockWithCoolDown {
    // 状态压缩后的 dp
    public int maxProfit(int[] prices) {
        // dp0 表示未持有股票的利润最大值
        // dp1 表示持有股票的利润最大值
        int dp0 = 0, dp1 = Integer.MIN_VALUE;
        int dp0Pre = 0;

        for (int price : prices) {
            int tmp = dp0;
            dp0 = Math.max(dp0, dp1 + price);
            dp1 = Math.max(dp1, dp0Pre - price);
            dp0Pre = tmp;
        }
        return dp0;
    }

    // 完整的 dp
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        int[][] dp = new int[n][2];

        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];


        for (int i = 1; i < n; i++) {
            if (i == 1) {
                dp[1][0] = Math.max(dp[0][0], prices[1] - prices[0]);
                dp[1][1] = Math.max(dp[0][1], -prices[1]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}
