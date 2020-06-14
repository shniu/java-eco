package io.github.shniu.arts.algothrim.leetcode.wordSearch;

/**
 * https://leetcode-cn.com/problems/word-search/
 * 79. 单词搜索
 */
public class WordSearch {
    private int rows;
    private int cols;
    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};

    // 1. 常规 dfs 解法
    // 时间复杂度 O(N*m*n*4^k)
    public boolean exist1(char[][] board, String word) {
        rows = board.length;
        cols = rows > 0 ? board[0].length : 0;

        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, visited, word, 0, i, j)) return true;
            }
        }

        return false;
    }

    // 时间复杂度：O(4^k) k 是单词的长度
    private boolean dfs(char[][] board, boolean[][] visited, String word, int pos, int row, int col) {
        // terminator
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
                visited[row][col] || word.charAt(pos) != board[row][col])
            return false;
        if (pos == word.length() - 1) return true;

        visited[row][col] = true;

        for (int i = 0; i < dx.length; i++) {
            if (dfs(board, visited, word, pos + 1, row + dx[i], col + dy[i]))
                return true;
        }

        visited[row][col] = false;
        return false;
    }

    // 2. 优化的 dfs, 做一些空间优化
    public boolean exist2(char[][] board, String word) {
        rows = board.length;
        cols = rows > 0 ? board[0].length : 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, 0, i, j)) return true;
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int pos, int row, int col) {
        // terminator
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
                board[row][col] == '$' || word.charAt(pos) != board[row][col])
            return false;
        if (pos == word.length() - 1) return true;

        char temp = board[row][col];
        board[row][col] = '$';

        for (int i = 0; i < dx.length; i++) {
            if (dfs(board, word, pos + 1, row + dx[i], col + dy[i]))
                return true;
        }

        board[row][col] = temp;
        return false;
    }

    // 3. 进一步小优化
    public boolean exist3(char[][] board, String word) {
        rows = board.length;
        cols = rows > 0 ? board[0].length : 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfsAdvanced(board, word, 0, i, j)) return true;
            }
        }

        return false;
    }

    private boolean dfsAdvanced(char[][] board, String word, int pos, int row, int col) {
        // terminator
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
                word.charAt(pos) != board[row][col])
            return false;
        if (pos == word.length() - 1) return true;

        board[row][col] ^= 256;

        for (int i = 0; i < dx.length; i++) {
            if (dfsAdvanced(board, word, pos + 1, row + dx[i], col + dy[i]))
                return true;
        }

        board[row][col] ^= 256;
        return false;
    }
}
