package io.github.shniu.algorithm.dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/5/6 16
 */
public class CompleteKnapsackIITest {

    @Test
    public void test_completeKnapsack() {
        CompleteKnapsackII knapsackII = new CompleteKnapsackII();

        int[] weights = new int[]{2, 4, 10, 5};
        int[] values = new int[]{1, 3, 5, 3};
        int w = 11;
        int n = 4;

        int maxValue = knapsackII.knapsack(weights, values, w, n);
        System.out.println("max -> " + maxValue);
    }

}