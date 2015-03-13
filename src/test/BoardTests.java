package test;

import static org.junit.Assert.assertEquals;
import game.Board;
import game.Move;
import game.Player;

import org.junit.Assert;
import org.junit.Test;

public class BoardTests {

    /**
     * Shouldn't allow a move into a full column.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testMakeMoveOnFullColumn() {
        Board board = new Board();
        for (int i = 0; i < Board.NUM_ROWS + 1; i++) {
            board.makeMove(new Move(i % 1 == 0 ? Player.RED : Player.YELLOW, 1));
        }
    }
    
    /**
     * Safely fill up first column.
     */
    @Test
    public void testMakeMoveFillColumn() {
        Board board = new Board();
        for (int i = 0; i < Board.NUM_ROWS; i++) {
            board.makeMove(new Move(i % 2 == 0 ? Player.RED : Player.YELLOW, 1));
        }
        for (int i = Board.NUM_ROWS; i < 0; i++) {
            assertEquals(board.getTile(i, 1), i % 2 == 0 ? Player.RED : Player.YELLOW);
        }
    }

    /**
     * All moves should be possible on a new board.
     */
    @Test
    public void testGetPossibleMovesNewBoard() {
        Board board = new Board();
        assertEquals(board.getPossibleMoves(Player.RED).length, Board.NUM_COLS);
    }

    /**
     * If the game is finished there should be no available moves.
     */
    @Test
    public void testGetPossibleMovesOnFinishedGame() {
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            board.makeMove(new Move(Player.RED, 1));
        }
        Assert.assertArrayEquals(new Move[]{}, board.getPossibleMoves(Player.RED));
    }

    /**
     * If a column is full it shouldn't be available as a possible move.
     */
    @Test
    public void testGetPossibleMovesWithFullColumn() {
        Board board = new Board();
        Player player = null;
        int column = 4;
        for (int i = 0; i < Board.NUM_ROWS; i++) {
            player = i % 2 == 0 ? Player.RED : Player.YELLOW;
            board.makeMove(new Move(player, column));
        }
        Move[] possibleMoves = board.getPossibleMoves(player.opponent());
        // There should be one less possible move than columns
        Assert.assertEquals(possibleMoves.length, Board.NUM_COLS - 1);
        // Column 'column' should not be an available move
        for (int i = 0; i < possibleMoves.length; i++) {
            Move move = possibleMoves[i];
            Assert.assertNotEquals(column, move.getColumn());
        }
    }

}
