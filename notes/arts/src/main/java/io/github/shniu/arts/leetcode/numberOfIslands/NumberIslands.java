package io.github.shniu.arts.leetcode.numberOfIslands;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/number-of-islands
 * 200. 岛屿数量
 */
public class NumberIslands {

    private int[] dx = new int[]{-1, 1, 0, 0};
    private int[] dy = new int[]{0, 0, -1, 1};
    private char[][] g;

    // 1. 使用 flood fill：https://zh.wikipedia.org/wiki/Flood_fill
    // 1.1 dfs
    public int numIslands11(char[][] grid) {
        int islands = 0;
        g = grid;

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == '0') continue;
                islands += sink(i, j);
            }
        }

        return islands;
    }

    private int sink(int i, int j) {
        // terminator
        if (g[i][j] == '0') return 0;

        // process current logic
        g[i][j] = '0';

        // drill down
        for (int k = 0; k < dx.length; k++) {
            int newx = i + dx[k], newy = j + dy[k];
            if (newx >= 0 && newx < g.length && newy >= 0 && newy < g[i].length) {
                if (g[newx][newy] == '0') continue;
                sink(newx, newy);
            }
        }

        return 1;
    }

    // 1.2
    public int numIslands12(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands += dfsMarking(grid, i, j);
                }
            }
        }
        return islands;
    }

    private int dfsMarking(char[][] grid, int i, int j) {
        // terminator
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1')
            return 0;

        // process current logic
        grid[i][j] = '0';

        // drill down
//        dfsMarking(grid, i + 1, j);
//        dfsMarking(grid, i - 1, j);
//        dfsMarking(grid, i, j + 1);
//        dfsMarking(grid, i, j - 1);
        for (int k = 0; k < dx.length; k++) {
            dfsMarking(grid, i + dx[k], j + dy[k]);
        }

        return 1;
    }

    // 1.3 bfs
    public int numIslands13(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    bfsFill(grid, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void bfsFill(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int n = grid.length;
        int m = grid[0].length;

        // bfs fill
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * m + j);
        while (!queue.isEmpty()) {
            int code = queue.poll();
            int r = code / m;
            int c = code % m;

            if (r > 0 && grid[r - 1][c] == '1') {
                queue.offer((r - 1) * m + c);
                grid[r - 1][c] = '0';
            }

            if (r < n - 1 && grid[r + 1][c] == '1') {
                queue.offer((r + 1) * m + c);
                grid[r + 1][c] = '0';
            }

            if (c > 0 && grid[r][c - 1] == '1') {
                queue.offer(r * m + (c - 1));
                grid[r][c - 1] = '0';
            }

            if (c < m - 1 && grid[r][c + 1] == '1') {
                queue.offer(r * m + (c + 1));
                grid[r][c + 1] = '0';
            }
        }
    }

    // 1.4 还有一种并查集的解法，union find set
    public int numIslands14(char[][] grid) {
        // todo union find solution
        return 0;
    }
}
