package test;

import static org.junit.Assert.*;
import game.Board;
import game.Dummy;
import game.Game;
import game.Move;
import game.Player;
import game.Solver;

import org.junit.Test;

public class GameTests {

    @Test
    public void testWinGame() {
        Board b= new Board();
        b.makeMove(new Move(Player.RED, 4));
        b.makeMove(new Move(Player.YELLOW, 3));
        b.makeMove(new Move(Player.RED, 5));
        Solver p1 = new Dummy(Player.RED);
        Solver p2 = new Dummy(Player.YELLOW);
        Game game= new Game(p1, p2, b, false);
        game.runGame();
        assertTrue(game.isGameOver());
        assertNotNull(game.getWinner());
    }

}
