package io.github.shniu.arts.leetcode.subsets;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubsetsTest {

    @Test
    public void subsets() {
        Subsets subsets = new Subsets();
        subsets.subsets1(new int[]{1, 2, 3});

        subsets.subsets2(new int[]{1, 2, 3});
    }
}