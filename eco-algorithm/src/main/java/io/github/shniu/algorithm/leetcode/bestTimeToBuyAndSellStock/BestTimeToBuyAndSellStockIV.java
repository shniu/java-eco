package io.github.shniu.algorithm.leetcode.bestTimeToBuyAndSellStock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * @author niushaohan
 * @date 2021/4/28 17
 */
public class BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        int days = prices.length;

        int[][][] dp = new int[days][k + 1][2];

        for (int i = 0; i < days; i++) {
            for (int j = k; j >= 1; j--) {
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }

                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[days - 1][k][0];
    }
}
