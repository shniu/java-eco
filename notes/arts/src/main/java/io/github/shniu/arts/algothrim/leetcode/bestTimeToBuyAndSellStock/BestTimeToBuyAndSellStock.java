package io.github.shniu.arts.algothrim.leetcode.bestTimeToBuyAndSellStock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 121. 买卖股票的最佳时机
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit1(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    // dp
    public int maxProfit2(int[] prices) {
        // dp0 表示不持有当天股票的最大利润
        // dp1 表示持有当天股票的最大利润
        int dp0 = 0, dp1 = Integer.MIN_VALUE;

        for (int price : prices) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1]+prices[i])
            dp0 = Math.max(dp0, dp1 + price);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp1 = Math.max(dp1, -price);
        }
        return dp0;
    }
}
