package io.github.shniu.arts.leetcode;

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
        TrieNode p = root;
        for (Character c : word.toCharArray()) {
            if (c == '.') {
                if (p.isEmpty()) {
                    return false;
                }
            } else {
                if (!p.containsKey(c)) {
                    return false;
                }
            }

            p = p.get(c);
        }

        return p != null && p.isEnd();
    }

}
