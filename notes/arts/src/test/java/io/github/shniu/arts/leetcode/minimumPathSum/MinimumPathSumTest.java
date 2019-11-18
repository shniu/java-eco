package io.github.shniu.arts.leetcode.minimumPathSum;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumPathSumTest {

    @Test
    public void minPathSum() {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int minPathSum = minimumPathSum.minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        assert minPathSum == 7;
    }
}