package io.github.shniu.arts.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrieTest {

    private Trie trie;

    @Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @Test
    public void testTrie() {
        boolean existsA = trie.search("a");
        Assert.assertFalse(existsA);

        boolean existsab = trie.startsWith("ab");
        Assert.assertFalse(existsab);

        trie.insert("hello");
        trie.insert("world");
        trie.insert("hub");
        trie.insert("hi");
        trie.insert("happy");

        boolean existshi = trie.search("hi");
        Assert.assertTrue(existshi);

        boolean existshel = trie.startsWith("hel");
        Assert.assertTrue(existshel);
    }
}