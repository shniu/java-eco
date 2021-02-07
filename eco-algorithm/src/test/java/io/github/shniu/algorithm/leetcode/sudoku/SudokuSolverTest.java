package io.github.shniu.algorithm.leetcode.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/2/5 17
 */
public class SudokuSolverTest {

    @Test
    public void test_sudokuSolver() {
        SudokuSolver sudokuSolver = new SudokuSolver();

        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '9', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '3'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '1'},
                {'.', '6', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '5'}
        };
        sudokuSolver.solveSudoku(board);
    }
}