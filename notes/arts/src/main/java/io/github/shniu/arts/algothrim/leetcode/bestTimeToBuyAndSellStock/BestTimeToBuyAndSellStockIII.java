package io.github.shniu.arts.algothrim.leetcode.bestTimeToBuyAndSellStock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 123. 买卖股票的最佳时机 III
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        // dp10 表示进行一次交易未持有股票的利润最大值
        // dp11 表示进行一次交易持有股票的利润最大值
        int dp10 = 0, dp11 = Integer.MIN_VALUE;
        // dp20 表示进行两次交易未持有股票的利润最大值
        // dp21 表示进行两次交易持有股票的利润最大值
        int dp20 = 0, dp21 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp20 = Math.max(dp20, dp21 + price);
            dp21 = Math.max(dp21, dp10 - price);
            dp10 = Math.max(dp10, dp11 + price);
            dp11 = Math.max(dp11, -price);
        }

        return dp20;
    }
}
