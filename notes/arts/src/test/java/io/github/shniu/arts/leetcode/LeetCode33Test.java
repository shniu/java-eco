package io.github.shniu.arts.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeetCode33Test {

    @Test
    public void findMinElementIndex() {
        int minElementIndex = -1;
        LeetCode33 leetCode33 = new LeetCode33();
        minElementIndex = leetCode33.findMinElementIndex(new int[]{10, 11, 12, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(minElementIndex);
        assert minElementIndex == 4;

        minElementIndex = leetCode33.findMinElementIndex(new int[]{10, 9});
        System.out.println(minElementIndex);
        assert minElementIndex == 1;

        minElementIndex = leetCode33.findMinElementIndex(new int[]{12, 13, 1, 2});
        System.out.println(minElementIndex);
        assert minElementIndex == 2;

        minElementIndex = leetCode33.findMinElementIndex(new int[]{12});
        System.out.println(minElementIndex);
        assert minElementIndex == 0;

        minElementIndex = leetCode33.findMinElementIndex(new int[]{12,13,14});
        System.out.println(minElementIndex);
        assert minElementIndex == 0;
    }
}