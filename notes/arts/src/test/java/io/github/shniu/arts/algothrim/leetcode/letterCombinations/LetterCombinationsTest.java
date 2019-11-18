package io.github.shniu.arts.algothrim.leetcode.letterCombinations;

import org.junit.Test;

import java.util.List;

public class LetterCombinationsTest {

    @Test
    public void letterCombinations() {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> combinations = letterCombinations.letterCombinations1("23");
        System.out.println(combinations);
    }
}