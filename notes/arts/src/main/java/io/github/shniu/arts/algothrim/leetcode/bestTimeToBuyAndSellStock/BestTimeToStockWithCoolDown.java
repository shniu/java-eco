package io.github.shniu.arts.algothrim.leetcode.bestTimeToBuyAndSellStock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 123. 买卖股票的最佳时机 III
 */
public class BestTimeToStockWithCoolDown {
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
}
