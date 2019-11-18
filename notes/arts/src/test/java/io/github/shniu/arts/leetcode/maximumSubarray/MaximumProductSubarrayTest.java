package io.github.shniu.arts.leetcode.maximumSubarray;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximumProductSubarrayTest {

    @Test
    public void maxProduct() {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        int maxProduct = maximumProductSubarray.maxProduct(new int[]{-2, 3, 4, -5, -5, 4});
        System.out.println(maxProduct);
        assert maxProduct == 1200;
    }
}