package io.github.shniu.algorithm.leetcode.palindrome;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/5/2 10
 */
public class PalindromeNumberTest {

    @Test
    public void isPalindrome() {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        boolean palindrome = palindromeNumber.isPalindrome(121);
        System.out.println(palindrome);
    }
}