package io.github.shniu.arts.algothrim.leetcode.anagrams;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindAllAnagramsInAStringTest {

    @Test
    public void findAnagrams() {
        FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();
        List<Integer> anagrams = findAllAnagramsInAString.findAnagrams1("cbaebabacd", "abc");
        System.out.println(anagrams);
    }
}