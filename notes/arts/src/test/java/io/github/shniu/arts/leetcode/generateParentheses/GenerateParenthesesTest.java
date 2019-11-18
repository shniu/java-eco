package io.github.shniu.arts.leetcode.generateParentheses;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GenerateParenthesesTest {

    @Test
    public void generateParenthesis1() {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> strings = generateParentheses.generateParenthesis1(3);
        System.out.println(strings);
    }

    @Test
    public void test_generateParenthesis2() {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> strings = generateParentheses.generateParenthesis2(3);
        System.out.println(strings);
    }

    @Test
    public void test_generateParenthesis3() {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> strings = generateParentheses.generateParenthesis3(3);
        System.out.println(strings);
    }
}