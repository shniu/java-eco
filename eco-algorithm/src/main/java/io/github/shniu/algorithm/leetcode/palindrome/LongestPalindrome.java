package io.github.shniu.algorithm.leetcode.palindrome;

/**
 * @author niushaohan
 * @date 2021/5/6 17
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null) return null;

        char[] chars = s.toCharArray();

        String res = "";
        for (int i = 0; i < chars.length; i++) {
            String s1 = longestPalindrome(s, i, i);
            String s2 = longestPalindrome(s, i, i + 1);

            res = s1.length() > res.length() ? s1 : res;
            res = s2.length() > res.length() ? s2 : res;
        }

        return res;
    }

    String longestPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        if ((right - 1) - (left + 1) >= 0) {
            return s.substring(left + 1, right);
        }

        return "";
    }

}
