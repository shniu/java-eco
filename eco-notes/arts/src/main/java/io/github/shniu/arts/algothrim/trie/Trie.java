package io.github.shniu.arts.algothrim.trie;

/**
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * 208. 实现 Trie (前缀树)
 *
 * Trie 树：如何实现搜索引擎的搜索关键词提示 https://time.geekbang.org/column/article/72414
 */
public class Trie {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {

        TrieNode p = root;
        for (Character c : word.toCharArray()) {
            if (!p.containsKey(c)) {
                p.put(c, new TrieNode());
            }
            p = p.get(c);
        }
        p.setEnd();

    }

    private TrieNode searchPrefix(String word) {
        TrieNode p = root;
        for (Character c : word.toCharArray()) {
            if (!p.containsKey(c)) {
                return null;
            }

            p = p.get(c);
        }

        return p;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode p = searchPrefix(word);
        return p != null && p.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode p = searchPrefix(prefix);
        return p != null;
    }
}

