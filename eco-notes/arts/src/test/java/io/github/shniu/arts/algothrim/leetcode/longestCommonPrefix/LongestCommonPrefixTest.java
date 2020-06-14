package io.github.shniu.arts.algothrim.leetcode.longestCommonPrefix;

import org.junit.Test;

public class LongestCommonPrefixTest {

    @Test
    public void longestCommonPrefix() {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String commonPrefix = longestCommonPrefix.longestCommonPrefix1(new String[]{
                "flower", "flow", "flight"
        });
        assert commonPrefix.equals("fl");
    }

}