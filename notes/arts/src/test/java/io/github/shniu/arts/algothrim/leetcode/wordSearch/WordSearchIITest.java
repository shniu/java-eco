package io.github.shniu.arts.algothrim.leetcode.wordSearch;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WordSearchIITest {

    @Test
    public void findWords() {
        WordSearchII wordSearchII = new WordSearchII();
        List<String> words = wordSearchII.findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain", "eae"});
        System.out.println(words);
    }

    @Test
    public void testFindWords_oneWord() {
        WordSearchII wordSearchII = new WordSearchII();
        List<String> words = wordSearchII.findWords(new char[][]{
                {'o'}
        }, new String[]{"o"});
        System.out.println(words);
    }

    @Test
    public void testFindWords2() {
        WordSearchII wordSearchII = new WordSearchII();
        List<String> words = wordSearchII.findWords(new char[][]{
                {'a', 'b'},
                {'a', 'a'}
        }, new String[]{"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"});
        System.out.println(words);
    }
}