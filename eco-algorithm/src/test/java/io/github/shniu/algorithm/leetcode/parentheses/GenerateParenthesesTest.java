package io.github.shniu.algorithm.leetcode.parentheses;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/2/5 16
 */
public class GenerateParenthesesTest {

    @Test
    public void test_generateParentheses() {
        GenerateParentheses parentheses = new GenerateParentheses();
        List<String> res = parentheses.generateParenthesis(3);
        System.out.println(res);
    }

    @Test
    public void test_generateParentheses2() {
        GenerateParentheses parentheses = new GenerateParentheses();
        List<String> res = parentheses.generateParenthesis2(3);
        System.out.println(res);
    }
}