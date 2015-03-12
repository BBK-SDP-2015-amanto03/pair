package test;

import static org.junit.Assert.assertNotNull;
import game.Board;
import game.Move;
import game.Player;

import org.junit.Test;

public class BoardTests {

    @Test(expected = UnsupportedOperationException.class)
    public void testMakeMoveOnFullColumn() {
        Board board = new Board();
        for (int i = 0; i < board.NUM_ROWS + 1; i++) {
            board.makeMove(new Move(i % 1 == 0 ? Player.RED : Player.YELLOW, 1));
        }
    }
    
    @Test
    public void testMakeMoveFillColumn() {
        Board board = new Board();
        for (int i = 0; i < board.NUM_ROWS; i++) {
            board.makeMove(new Move(i % 1 == 0 ? Player.RED : Player.YELLOW, 1));
        }
        for (int i = 0; i < board.NUM_ROWS; i++) {
            assertNotNull(board.getTile(i, 1));
        }
    }

}
