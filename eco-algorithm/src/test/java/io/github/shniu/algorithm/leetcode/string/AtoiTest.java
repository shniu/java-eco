package io.github.shniu.algorithm.leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/16 18
 */
public class AtoiTest {

    @Test
    public void strToInt() {
        Atoi atoi = new Atoi();
        int res = atoi.strToInt("123");
        assertEquals(123, res);

        res = atoi.strToInt("-123");
        assertEquals(-123, res);

        res = atoi.strToInt("  -123");
        assertEquals(-123, res);

        res = atoi.strToInt("  123");
        assertEquals(123, res);

        res = atoi.strToInt("   123 ");
        assertEquals(123, res);

        res = atoi.strToInt("123-");
        assertEquals(123, res);

        res = atoi.strToInt("-123-");
        assertEquals(-123, res);

        res = atoi.strToInt("-123-21");
        assertEquals(-123, res);

        res = atoi.strToInt(" abc-123-");
        assertEquals(0, res);

        res = atoi.strToInt(" ab*& - 123-");
        assertEquals(0, res);

        res = atoi.strToInt(" ab*& - 123ab e9083");
        assertEquals(0, res);

        res = atoi.strToInt("   anye wee-efw aa");
        assertEquals(0, res);

        res = atoi.strToInt(" 000 123");
        assertEquals(123, res);

        res = atoi.strToInt(" 000-");
        assertEquals(0, res);

        res = atoi.strToInt(" 000123an-012");
        assertEquals(123, res);

        // -91283472332
        res = atoi.strToInt("-91283472332");
        assertEquals(Atoi.MIN, res);

        res = atoi.strToInt("+91283472332");
        assertEquals(Atoi.MAX, res);

        // 1 << 1 -> 10
        System.out.println(-1 << 31);
        System.out.println(Integer.toBinaryString(-1 << 31));
        System.out.println(Integer.toBinaryString(1 << 31));
        System.out.println((1 << 31) - 1);

        System.out.println(Atoi.MAX);
        System.out.println(Atoi.MAX * 10);

    }

    @Test
    public void test_atoi() {
        Atoi2 atoi2 = new Atoi2();
        atoi2.strToInt("42");
    }
}