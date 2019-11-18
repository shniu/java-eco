package io.github.shniu.arts.algothrim.leetcode;

import org.junit.Test;

public class LeetCode69Test {

    @Test
    public void mySqrt() {
        LeetCode69 leetCode69 = new LeetCode69();
        int i0 = leetCode69.mySqrt(8);
        int i1 = leetCode69.mySqrt(1);
        int i2 = leetCode69.mySqrt(2);
        int i4 = leetCode69.mySqrt(4);
        int i10 = leetCode69.mySqrt(10);
        int i99 = leetCode69.mySqrt(99);

        assert i0 == 2;
        assert i1 == 1;
        assert i2 == 1;
        assert i4 == 2;
        assert i10 == 3;
        System.out.println(i99);
        assert i99 == 9;
    }
}