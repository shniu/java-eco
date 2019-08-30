package io.github.shniu.arts.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordDictionaryTest {

    private WordDictionary wordDictionary;

    @Before
    public void setUp() throws Exception {
        wordDictionary = new WordDictionary();
    }

    @Test
    public void testWordDictionary() {
        boolean r1 = wordDictionary.search("abc");
        Assert.assertFalse(r1);

        boolean r2 = wordDictionary.search("a.c");
        Assert.assertFalse(r2);

        boolean r3 = wordDictionary.search("ab.");
        Assert.assertFalse(r3);

        wordDictionary.addWord("aaa");
        wordDictionary.addWord("abc");
        wordDictionary.addWord("abcd");
        wordDictionary.addWord("abbb");
        wordDictionary.addWord("cbc");

        boolean r4 = wordDictionary.search("ab");
        Assert.assertFalse(r4);

        boolean r5 = wordDictionary.search("ab.");
        Assert.assertTrue(r5);

        boolean r6 = wordDictionary.search(".bc");
        Assert.assertTrue(r6);

        boolean r7 = wordDictionary.search("a.b.");
        Assert.assertTrue(r7);
    }
}