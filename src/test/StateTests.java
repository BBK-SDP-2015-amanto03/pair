package test;

import static org.junit.Assert.assertEquals;
import game.Board;
import game.Move;
import game.Player;
import game.State;

import org.junit.Test;

public class StateTests {

    /**
     * State should be a leaf node if the game is over.
     */
    @Test
    public void testInitializeChildrenNoMoreMoves() {
        Board board = new Board();
        for (int i = 0; i < 4; i++) {
            board.makeMove(new Move(Player.RED, 1));
        }
        State state = new State(Player.YELLOW,board,null);
        state.initializeChildren();
        assertEquals(0, state.getChildren().length);
    }

}
