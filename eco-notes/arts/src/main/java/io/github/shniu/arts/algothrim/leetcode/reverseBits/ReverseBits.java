package io.github.shniu.arts.algothrim.leetcode.reverseBits;

/**
 * https://leetcode-cn.com/problems/reverse-bits/
 * 190. 颠倒二进制位
 */
public class ReverseBits {
    public int reverseBits1(int n) {
        System.out.println(Integer.toBinaryString(n));
        int res = 0;
        // 32 bit, loop 32 times
        for (int i = 0; i < 32; i++) {
            // n & 1： 取二进制最后一位
            // res << 1： res 左移1位，如 0 << 1 -> 00
            int a = n & 1;
            int b = res << 1;
            res = a + b;
            System.out.println(Integer.toBinaryString(a));
            System.out.println(Integer.toBinaryString(b));
            System.out.println(Integer.toBinaryString(res));
            // res = (res << 1) + (n & 1);

            // n 右移1位，删除掉最低位
            n = n >> 1;  // n >>= 1;
        }
        return res;

        /*int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) +  (n & 1);
            n >>= 1;
        }
        return res;*/
    }

    public int reverseBits2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>= 1;
        }
        return res;
    }
}
