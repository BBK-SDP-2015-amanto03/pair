package test;

import static org.junit.Assert.assertTrue;
import game.AI;
import game.Board;
import game.Game;
import game.Player;
import game.Solver;

import org.junit.Test;

public class GameTests {

    /**
     * End to end integration test.
     */
    @Test
    public void testWinGame() {
        Board b= new Board();
        Solver p1 = new AI(Player.RED, 4);
        Solver p2 = new AI(Player.YELLOW, 4);
        Game game= new Game(p1, p2, b, false);
        game.runGame();
        assertTrue(game.isGameOver());
    }

}
