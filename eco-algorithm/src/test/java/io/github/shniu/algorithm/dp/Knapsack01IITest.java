package io.github.shniu.algorithm.dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/5/6 14
 */
public class Knapsack01IITest {

    @Test
    public void knapsack001() {
        Knapsack01II knapsack01II = new Knapsack01II();

        int[] weights = new int[]{2, 4, 10, 5};
        int[] values = new int[]{1, 3, 100, 3};
        int w = 11;
        int n = 4;
        int maxValue = knapsack01II.knapsack001(weights, values, w, n);
        System.out.println("001 -> " + maxValue);

        maxValue = knapsack01II.knapsack002(weights, values, w, n);
        System.out.println("002 -> " + maxValue);

        maxValue = knapsack01II.knapsack01(weights, values, w, n);
        System.out.println("000 -> " + maxValue);
    }
}