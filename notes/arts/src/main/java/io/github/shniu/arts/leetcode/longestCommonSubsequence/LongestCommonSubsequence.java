package io.github.shniu.arts.leetcode.longestCommonSubsequence;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 1143. 最长公共子序列
 */
public class LongestCommonSubsequence {
    // 1. dp
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        if (text1.isEmpty() || text2.isEmpty()) return 0;

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // 2. 优化版的dp
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length() + 1, n = text2.length() + 1;
        // 状态表使用一维数组，前面已经计算过的已无效
        int[] dp = new int[n];
        // 在两个字符串前面追加“#”,防止边界越界，不要判断边界是否大于0，提升效率
        char[] text1Arr = ("#" + text1).toCharArray();
        char[] text2Arr = ("#" + text2).toCharArray();
        // 用来记录(i-1,j-1)位置的最长子序列
        int temp, now;
        for (int i = 1; i < m; i++) {
            temp = 0;
            for (int j = 1; j < n; j++) {
                // 记录(i-1, j-1)位置的最长子序列长度
                now = dp[j];
                if (text1Arr[i] == text2Arr[j]) {
                    // 要去 (i-1, j-1) 位置的值
                    dp[j] = temp + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                // 记录(i-1, j-1)位置的最长子序列长度，传递到(i,j)
                temp = now;
            }
        }
        return dp[n - 1];
    }
}
