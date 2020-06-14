package io.github.shniu.arts.algothrim.leetcode.surroundedRegions;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SurroundedRegionsTest {

    @Test
    public void solve2() {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char[][] board = new char[][]{
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}
        };
        surroundedRegions.solve2(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + ", ");
            }
            System.out.println();
        }
    }
}