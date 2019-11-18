package io.github.shniu.arts.leetcode;

import io.github.shniu.arts.trie.TrieNode;

public class WordDictionary {
    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (Character c : word.toCharArray()) {
            if (!p.containsKey(c)) {
                p.put(c, new TrieNode());
            }
            p = p.get(c);
        }
        p.setEnd();
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word, root, 0);
    }

    private boolean match(String word, TrieNode node, int start) {
        if (start == word.length()) {
            return node.isEnd();
        }

        char c = word.charAt(start);

        if (c == '.') {
            TrieNode tmp;
            for (int i = 0; i < 26; i++) {
                tmp = node.get(i);
                if (tmp != null && match(word, tmp, start + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            node = node.get(c);
            if (node == null) {
                return false;
            }
            return match(word, node, start + 1);
        }
    }

}
