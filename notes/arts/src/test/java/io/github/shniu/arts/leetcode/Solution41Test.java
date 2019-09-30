package io.github.shniu.arts.leetcode;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solution41Test {

    @Test
    public void test_firstMissingPositive() {
        Solution41 solution41 = new Solution41();

        int[] nums = {10, 2, -1, 30};
        int firstMissingPositive = solution41.firstMissingPositive(nums);
        Assertions.assertThat(firstMissingPositive).isEqualTo(1);

        int[] nums2 = {10, 3, 5, 1, 100, 2, -1};
        firstMissingPositive = solution41.firstMissingPositive(nums2);
        Assertions.assertThat(firstMissingPositive).isEqualTo(4);


        firstMissingPositive = solution41.firstMissingPositive2(nums2);
        Assertions.assertThat(firstMissingPositive).isEqualTo(4);
    }
}