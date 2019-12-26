package io.github.shniu.arts.algothrim.leetcode.regularExpressionMatching;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegularExpressionMatchingTest {

    @Test
    public void normalMatch() {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        boolean match = regularExpressionMatching.normalMatch("abcdeffff", "abc.ef*");
        System.out.println(match);
    }
}