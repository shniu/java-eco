package io.github.shniu.algorithm.leetcode.other;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/5/3 14
 */
public class ReverseIntegerTest {

    @Test
    public void test_reverseInteger() {
        ReverseInteger reverseInteger = new ReverseInteger();
        int reversed = reverseInteger.reverse(123);

        System.out.println(reversed);

        reversed = reverseInteger.reverse(-123);
        System.out.println(reversed);

        reversed = reverseInteger.reverse(-2147483648);
        System.out.println(reversed);

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }
}