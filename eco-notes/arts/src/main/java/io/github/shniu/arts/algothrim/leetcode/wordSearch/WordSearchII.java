package io.github.shniu.arts.algothrim.leetcode.wordSearch;

import io.github.shniu.arts.algothrim.trie.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/word-search-ii/
 * 212. 单词搜索 II
 */
public class WordSearchII {
    private int rows;
    private int cols;
    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        rows = board.length;
        cols = rows > 0 ? board[0].length : 0;

        // 将 words 构建一个 Trie
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        // dfs
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                dfs(board, trie, row, col, res, "");
            }
        }

        return new LinkedList<>(res);
    }

    private void dfs(char[][] board, Trie trie, int row, int col, Set<String> res, String s) {
        // terminator
        if (row < 0 || row >= rows || col < 0 || col >= cols) return;

        String ss = s + board[row][col];
        if (!trie.startsWith(ss)) return;
        if (trie.search(ss)) {
            res.add(ss);
        }

        board[row][col] ^= 256;

        // drill down
        for (int i = 0; i < dx.length; i++) {
            // if (row + dx[i] >= 0 && row + dx[i] < rows && col + dy[i] >= 0 && col + dy[i] < cols)
            dfs(board, trie, row + dx[i], col + dy[i], res, ss);
        }

        board[row][col] ^= 256;
    }
}
