package test;

import static org.junit.Assert.assertTrue;
import game.AI;
import game.Board;
import game.Move;
import game.Player;
import game.Solver;
import game.State;

import org.junit.Test;

public class AITests {

    /**
     * Test that the AI can find winning moves.
     */
    @Test
    public void testGetWinningMoves() {
        Board board = new Board();
        board.makeMove(new Move(Player.RED, 1));
        board.makeMove(new Move(Player.YELLOW, 2));
        board.makeMove(new Move(Player.RED, 2));
        board.makeMove(new Move(Player.YELLOW, 1));
        board.makeMove(new Move(Player.RED, 3));
        board.makeMove(new Move(Player.YELLOW, 3));
        board.makeMove(new Move(Player.RED, 3));
        board.makeMove(new Move(Player.YELLOW, 0));
        board.makeMove(new Move(Player.RED, 4));
        board.makeMove(new Move(Player.YELLOW, 5));
        board.makeMove(new Move(Player.RED, 2));
        board.makeMove(new Move(Player.YELLOW, 4));
        board.makeMove(new Move(Player.RED, 4));
        board.makeMove(new Move(Player.YELLOW, 6));
        // Red has two winning moves here. Will it spot that?
        Solver ai = new AI(Player.RED, 6);
        Move[] moves = ai.getMoves(board);
        assertTrue(moves.length == 2);
        assertTrue(moves[0].getPlayer() == Player.RED);
        assertTrue(moves[0].getColumn() == 1);
        assertTrue(moves[1].getPlayer() == Player.RED);
        assertTrue(moves[1].getColumn() == 4);
    }

    /**
     * Test that the AI can block opponents' winning moves.
     */
    @Test
    public void testGetBlockingMoves() {
        Board board = new Board();
        board.makeMove(new Move(Player.YELLOW, 0));
        board.makeMove(new Move(Player.RED, 0));
        board.makeMove(new Move(Player.YELLOW, 1));
        board.makeMove(new Move(Player.RED, 0));
        board.makeMove(new Move(Player.YELLOW, 2));
        // Yellow has a winning next move. Will RED spot that?
        Solver ai = new AI(Player.RED, 5);
        Move[] moves = ai.getMoves(board);
        assertTrue(moves.length == 1);
        assertTrue(moves[0].getPlayer() == Player.RED);
        assertTrue(moves[0].getColumn() == 3);
    }
    
    /**
     * Test the game tree creates a leaf node on a winning game.
     */
    @Test
    public void testWinningStateIsLeafNode() {
        Board board = new Board();
        // Set up a winning board.
        board.makeMove(new Move(Player.YELLOW, 0));
        board.makeMove(new Move(Player.RED, 0));
        board.makeMove(new Move(Player.YELLOW, 1));
        board.makeMove(new Move(Player.RED, 0));
        board.makeMove(new Move(Player.YELLOW, 2));
        board.makeMove(new Move(Player.RED, 0));
        board.makeMove(new Move(Player.YELLOW, 3));
        assertTrue(board.hasConnectFour().equals(Player.YELLOW));
        State winningState = new State(Player.RED, board, null);
        AI.createGameTree(winningState, 100);
        assertTrue(winningState.getChildren().length == 0);
    }
    
    /**
     * Confirm Null Pointer Exception on getMoves with null board.
     */
    @Test(expected=NullPointerException.class)
    public void testNullPointerOnNullBoard() {
        Solver ai = new AI(Player.RED, 10);
        ai.getMoves(null);
    }
    
    
}
