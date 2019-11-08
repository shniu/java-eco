package io.github.shniu.arts.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LeetCode22Test {

    @Test
    public void generateParenthesis() {
        LeetCode22 leetCode22 = new LeetCode22();
        List<String> strings = leetCode22.generateParenthesis1(3);
        System.out.println(strings);

        strings = leetCode22.generateParenthesis3(3);
        System.out.println(strings);
    }
}