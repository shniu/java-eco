package io.github.shniu.arts.algothrim.leetcode;

import io.github.shniu.arts.algothrim.leetcode.wordLadder.WordLadder;
import org.junit.Test;

import java.util.ArrayList;

public class WordLadderTest {

    @Test
    public void ladderLength() {
        WordLadder wordLadder = new WordLadder();
        int minConversionCount = wordLadder.ladderLength1("hit", "cog", new ArrayList<String>() {{
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