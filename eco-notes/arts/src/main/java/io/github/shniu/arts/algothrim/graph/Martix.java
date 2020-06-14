package io.github.shniu.arts.algothrim.graph;

import java.util.Arrays;

public class Martix {

    // 邻接矩阵的dfs实现
    public void testDfs(int[][] M) {
        int[] visited = new int[M.length];

        for (int i = 0; i < M.length; i++) {
            System.out.println("---------------- " + i + " ----------------");
            System.out.println(Arrays.toString(visited));
            if (visited[i] == 0)
                dfs(M, visited, i);
        }
    }

    private void dfs(int[][] m, int[] visited, int i) {
        for (int j = 0; j < m.length; j++) {
            System.out.println("\t******** " + j + " ********");
            if (m[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                System.out.println("\tvisited: " + i + ", " + j);
                dfs(m, visited, j);
            }
        }
    }

}
