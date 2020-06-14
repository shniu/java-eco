package io.github.shniu.arts.algothrim.leetcode.longestValidParentheses;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 32. 最长有效括号
 */
public class LongestValidParentheses {

    // 1. 暴力法
    // 对每个偶数长度的字符串都进行比较，计算最长的有效长度
    public int longestValidParentheses1(String s) {
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                // 取 i...j
                if (isValid(s.substring(i, j))) {
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }

        return maxLen;
    }

    // 判断子串是否合法
    private boolean isValid(String substr) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < substr.length(); i++) {
            if (substr.charAt(i) == '(') {
                stack.addFirst('(');
            } else {
                if (stack.isEmpty() || stack.removeFirst() != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // 2. dp, 暴力解法复杂度太高
    public int longestValidParentheses2(String s) {
        int maxLen = 0;

        // dp[i] 表示以第i个元素结尾的最长有效括号长度
        int[] dp = new int[s.length()];

        // 1. 形如 ....(), dp[i] = dp[i-2] + 2
        // 2. 形如 ....)), if s.charAt(i-dp[i-1]-1)==( then dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    int prev = i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0;
                    dp[i] = dp[i - 1] + 2 + prev;
                }

                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }

    // 3. stack
    public int longestValidParentheses3(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addFirst(i);
            } else { // )
                stack.removeFirst();
                if (stack.isEmpty()) {
                    stack.addFirst(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peekFirst());
                }
            }
        }
        return maxLen;
    }
}
