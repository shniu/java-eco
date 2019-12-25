package io.github.shniu.arts.algothrim.leetcode.wildcardMatching;

/**
 * https://leetcode-cn.com/problems/wildcard-matching/
 * 44. 通配符匹配
 */
public class WildcardMatching {
    // 1. 双指针, 3 ms
    // 双指针的思想是尽可能多的匹配模式串，属于 DFS
    // 如果匹配失败，就回溯到上一次*出现的位置
    // https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
    public boolean isMatch1(String s, String p) {
        int sp = 0, pp = 0, star = -1, match = 0;

        while (sp < s.length()) {
            // s指针指向的位置和p指针指向的位置字符相等或者p指针对应的是通配符?
            if (pp < p.length() && (p.charAt(pp) == '?' || s.charAt(sp) == p.charAt(pp))) {
                sp++;
                pp++;
            }
            // * found，移动p指针
            else if (pp < p.length() && p.charAt(pp) == '*') {
                star = pp;
                match = sp;
                pp++;
            }
            // p指针的上一个位置是*，移动s指针
            else if (star != -1) {
                pp = star + 1;  // reset pp，回溯到上一次*出现的位置
                match++;
                sp = match;
            }
            // p指针不是*，p指针的上一个位置也不是*，且对应位置的字符不匹配
            else {
                return false;
            }
        }

        // 检查p剩下的字符
        while (pp < p.length() && p.charAt(pp) == '*') pp++;

        return pp == p.length();
    }

    // 2. dp
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
