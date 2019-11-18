package io.github.shniu.arts.algothrim.trie;

public class TrieNode {

    private TrieNode[] links;
    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public TrieNode get(int position) {
        return links[position];
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public boolean isEmpty() {
        for (TrieNode n : links) {
            if (n != null) {
                return false;
            }
        }

        return true;
    }
}
