package io.github.shniu.arts.algothrim.leetcode.editDistance;

/**
 * https://leetcode-cn.com/problems/edit-distance
 * 72. 编辑距离
 */
public class EditDistance {
    // 1. 递归解法
    public int minDistance1(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] memo = new int[len1][len2];
        return minDistance1(word1, word2, len1 - 1, len2 - 1, memo);
    }

    private int minDistance1(String word1, String word2, int i, int j, int[][] memo) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (memo[i][j] != 0) return memo[i][j];

        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = minDistance1(word1, word2, i - 1, j - 1, memo);
        } else {
            memo[i][j] = Math.min(minDistance1(word1, word2, i - 1, j - 1, memo) + 1,
                    Math.min(minDistance1(word1, word2, i, j - 1, memo) + 1, minDistance1(word1, word2, i - 1, j, memo) + 1));
        }
        return memo[i][j];
    }

    // 2. DP
    public int minDistance2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        // base case
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
                }
            }
        }

        return dp[len1][len2];
    }
}
