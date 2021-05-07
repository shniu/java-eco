package io.github.shniu.algorithm.leetcode.other;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/5/6 16
 */
public class DecodeXoredArrayTest {

    @Test
    public void decode() {
        int a = 2;
        int b = 7;
        int c = a ^ b;

        int d = c ^ a;

        System.out.println("a -> " + a + ", b -> " + b + ", c = a ^ b -> " + c);
        System.out.println("c -> " + c + ", a -> " + a + ", d = c ^ a -> " + d);
    }
}