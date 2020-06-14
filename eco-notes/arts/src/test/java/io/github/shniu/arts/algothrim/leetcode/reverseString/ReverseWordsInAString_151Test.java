package io.github.shniu.arts.algothrim.leetcode.reverseString;

import org.junit.Test;

public class ReverseWordsInAString_151Test {

    @Test
    public void reverseWords() {
        ReverseWordsInAString_151 reverseWordsInAString = new ReverseWordsInAString_151();
        String s = reverseWordsInAString.reverseWords1("the sky   is blue");
        System.out.println(s);

        s = reverseWordsInAString.reverseWords1("");
        System.out.println(s);
    }
}