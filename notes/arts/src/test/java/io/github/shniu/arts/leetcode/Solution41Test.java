package io.github.shniu.arts.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class Solution41Test {

    @Test
    public void test_firstMissingPositive() {
        Solution41 solution41 = new Solution41();

        int[] nums = {10, 2, -1, 30};
        solution41.firstMissingPositive(nums);
    }
}