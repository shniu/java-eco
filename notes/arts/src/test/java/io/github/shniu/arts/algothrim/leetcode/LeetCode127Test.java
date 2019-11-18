package io.github.shniu.arts.algothrim.leetcode;

import org.junit.Test;

import java.util.ArrayList;

public class LeetCode127Test {

    @Test
    public void ladderLength() {
        LeetCode127 leetCode127 = new LeetCode127();
        int minConversionCount = leetCode127.ladderLength("hit", "cog", new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }});

        assert minConversionCount == 5;
    }
}