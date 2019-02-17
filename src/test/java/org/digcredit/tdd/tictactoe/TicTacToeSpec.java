package org.digcredit.tdd.tictactoe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test
 * Created by shniu on 2019/2/15 0015.
 */
public class TicTacToeSpec {

    private TicTacToe ticTacToe;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(1, 1);
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(1, 1);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        Assert.assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1, 1);
        Assert.assertEquals('O', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        Assert.assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        String actual = ticTacToe.play(3, 1);
        Assert.assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1);
        ticTacToe.play(1, 1);
        ticTacToe.play(3, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        String actual = ticTacToe.play(1, 3);
        Assert.assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        ticTacToe.play(1, 3);
        String actual = ticTacToe.play(3, 3);
        Assert.assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3);
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 2);
        ticTacToe.play(1, 2);
        String actual = ticTacToe.play(3, 1);
        Assert.assertEquals("X is the winner", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        Assert.assertEquals("The result is draw", actual);
    }

}
