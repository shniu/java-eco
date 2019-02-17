package org.digcredit.tdd.tictactoe;

/**
 * Tic tac toe
 * <p>
 * Created by shniu on 2019/2/15 0015.
 */
public class TicTacToe {

    private final int SIZE = 3;
    private char lastPlayer = '\0';

    private Character[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}
    };

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);

        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);

        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        }

        if (isDraw()) {
            return "The result is draw";
        }

        return "No winner";
    }

    private boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * 3;
        int diagonal1 = '\0';
        int diagonal2 = '\0';
        int horizontal = '\0';
        int vertical = '\0';

        for (int index=0; index < SIZE; index++) {

            diagonal1 += board[index][index];
            diagonal2 += board[index][SIZE - index - 1];
            horizontal += board[index][y - 1];
            vertical += board[x - 1][index];

            /*if (board[0][index] + board[1][index] + board[2][index] == playerTotal) {
                return true;
            } else if (board[index][0] + board[index][1] + board[index][2] == playerTotal) {
                return true;
            }*/

            if (horizontal == playerTotal || vertical == playerTotal ||
                    diagonal1 == playerTotal || diagonal2 == playerTotal) {
                return true;
            }
        }

        return false;
    }

    private void setBox(int x, int y, char lastPlayer) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = lastPlayer;
        }
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }
}
