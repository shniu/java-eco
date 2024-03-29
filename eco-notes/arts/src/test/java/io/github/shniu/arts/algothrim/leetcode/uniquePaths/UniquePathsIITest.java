package io.github.shniu.arts.algothrim.leetcode.uniquePaths;

import org.junit.Test;

public class UniquePathsIITest {

    @Test
    public void uniquePathsWithObstacles() {
        UniquePathsII uniquePathsII = new UniquePathsII();
        int paths = uniquePathsII.uniquePathsWithObstacles(new int[][]{
                {0, 1},
                {0, 0}
        });
        assert paths == 1;

        paths = uniquePathsII.uniquePathsWithObstacles(new int[][]{
                {0, 1},
                {1, 0}
        });
        assert paths == 0;

        paths = uniquePathsII.uniquePathsWithObstacles(new int[][]{
                {1, 0},
                {0, 0}
        });
        assert paths == 0;

        paths = uniquePathsII.uniquePathsWithObstacles(new int[][]{
                {0, 1}
        });
        assert paths == 0;

        paths = uniquePathsII.uniquePathsWithObstacles(new int[][]{
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 0}
        });
        assert paths == 1;
    }
}