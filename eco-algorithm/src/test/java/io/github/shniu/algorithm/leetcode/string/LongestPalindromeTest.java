package io.github.shniu.algorithm.leetcode.string;

import io.github.shniu.algorithm.leetcode.palindrome.LongestPalindrome;
import org.junit.Test;

/**
 * @author niushaohan
 * @date 2021/5/6 17
 */
public class LongestPalindromeTest {

    @Test
    public void longestPalindrome() {
        LongestPalindrome palindrome = new LongestPalindrome();
        String longestPalindrome = palindrome.longestPalindrome("babad");
        System.out.println(longestPalindrome);
    }
}