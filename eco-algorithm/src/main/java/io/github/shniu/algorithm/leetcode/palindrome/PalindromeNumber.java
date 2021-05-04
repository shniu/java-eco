package io.github.shniu.algorithm.leetcode.palindrome;

/**
 * @author niushaohan
 * @date 2021/5/2 10
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);

        if (s.length() % 2 == 0) {
            return isPalindrome(s.toCharArray(), (s.length() / 2) - 1, s.length() / 2);
        } else {
            return isPalindrome(s.toCharArray(), s.length() / 2, s.length() / 2);
        }
    }

    boolean isPalindrome(char[] chars, int left, int right) {
        while (left >= 0 && right <= chars.length - 1
                && chars[left] == chars[right]) {
            left--;
            right++;
        }

        return left == -1 && right == chars.length;
    }
}
