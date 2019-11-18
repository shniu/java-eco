package io.github.shniu.arts.algothrim.leetcode.slidingWindow;

import org.junit.Test;

import java.util.Arrays;

public class SlidingWindowMaximumTest {

    @Test
    public void maxSlidingWindow() {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] res;
//        res = slidingWindowMaximum.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        System.out.println(Arrays.toString(res));

        res = slidingWindowMaximum.maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
        System.out.println(Arrays.toString(res));
    }
}