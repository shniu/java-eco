package io.github.shniu.arts.algothrim.leetcode.reverseOnlyLetters;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseOnlyLettersTest {


    @Test
    public void testOnlyLetters() {
        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        String letters = reverseOnlyLetters.reverseOnlyLetters("a-bC-dEf-ghIj");
        System.out.println(letters);
        assert letters.equals("j-Ih-gfE-dCba");


        letters = reverseOnlyLetters.reverseOnlyLetters("Test1ng-Leet=code-Q!");
        System.out.println(letters);
        assert letters.equals("Qedo1ct-eeLg=ntse-T!");

        letters = reverseOnlyLetters.reverseOnlyLetters("------a---b");
        System.out.println(letters);
        assert letters.equals("------b---a");

        letters = reverseOnlyLetters.reverseOnlyLetters("7_28]");
        System.out.println(letters);
        assert letters.equals("7_28]");
    }
}