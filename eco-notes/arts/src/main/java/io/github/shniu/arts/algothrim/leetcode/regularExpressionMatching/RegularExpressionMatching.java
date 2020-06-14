package io.github.shniu.arts.algothrim.leetcode.regularExpressionMatching;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 * 10. 正则表达式匹配
 * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/dong-tai-gui-hua-zhi-zheng-ze-biao-da
 * https://leetcode.wang/leetCode-10-Regular-Expression-Matching.html
 */
public class RegularExpressionMatching {

    // 记忆化递归
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // memo 数组
        Boolean[][] memo = new Boolean[m + 1][n + 1];
        // 加入两个位置参数，减少字符串的生成操作，优化时间和空间
        return isMatch(s, p, memo, 0, 0);
    }

    private boolean isMatch(String s, String p, Boolean[][] memo, int i, int j) {
        // terminator
        if (j == p.length()) return i == s.length();
        if (memo[i][j] != null) return memo[i][j];

        boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        if (j <= p.length() - 2 && p.charAt(j + 1) == '*') {
            memo[i][j] = isMatch(s, p, memo, i, j + 2) ||
                    (firstMatch && isMatch(s, p, memo, i + 1, j));
        } else {
            memo[i][j] = firstMatch && isMatch(s, p, memo, i + 1, j + 1);
        }

        return memo[i][j];
    }


    // 暴力递归
    public boolean normalMatch(String s, String p) {
        // terminator
        if (p.length() == 0) return s.length() == 0;

        // process current logic
        // 判端第一个字符是否匹配
        boolean firstMatch = false;
        if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
            firstMatch = true;
        }

        // 模式串长度大于等于2且第二个位置是*
        // 将问题分解为两部分：
        //   1. 匹配0次*之前的字符串，看剩余的字符串是否匹配 -> s[...], p[2...]
        //   2. 或者 匹配1次*之前的字符串，看剩余的字符串是否匹配 -> s[1..], p[...]
        // 经过 1 和 2 的有限次运算就可以组合出所有情况
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return normalMatch(s, p.substring(2)) ||
                    (firstMatch && normalMatch(s.substring(1), p));
        } else {
            // 否则直接挨个匹配，取s和p的子串进行匹配
            // 将问题分解为第一个字符是否匹配 & 剩下的字符是否匹配
            return firstMatch && normalMatch(s.substring(1), p.substring(1));
        }
    }

    // dp
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // base case
        dp[m][n] = true;

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m && j == n) continue;

                boolean firstMatch = i < m && j < n &&
                        (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                if (j <= n - 2 && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
