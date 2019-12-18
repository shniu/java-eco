package io.github.shniu.arts.algothrim.leetcode.bestTimeToBuyAndSellStock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 122. 买卖股票的最佳时机 II
 */
public class BestTimeToBuyAndSellStockII {

    // dp
    public int maxProfit(int[] prices) {
        int dp0 = 0, dp1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int tmp = dp0;
            dp0 = Math.max(dp0, dp1 + price);
            dp1 = Math.max(dp1, tmp - price);
        }
        return dp0;
    }

    // 贪心
    // 该题使用贪心法是有效的
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int delta = prices[i] - prices[i - 1];
            if (delta > 0) {
                maxProfit += delta;
            }
        }
        return maxProfit;
    }
}
