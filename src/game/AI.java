package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An instance represents a Solver that intelligently determines
 * Moves using the Minimax algorithm.
 */
public class AI implements Solver {

    private final Player player; // the current player

    /**
     * The depth of the search in the game space when evaluating moves.
     */
    private final int depth;

    /**
     * Constructor: an instance with player p who searches to depth d
     * when searching the game space for moves.
     */
    public AI(Player p, int d) {
        player = p;
        depth = d;
    }

    /**
     * See Solver.getMoves for the specification.
     */
    @Override
    public Move[] getMoves(Board b) {
        // Get the current state of affairs. We're going to play but 
        // we don't know what the last move was.
        State currentState = new State(player, b, null);
        // Create the game tree.
        createGameTree(currentState, depth);
        // Evaluate the tree
        minimax(this, currentState);
        // Return all children with the best move value (there could be more than one).
        List<Move> bestMoves = new ArrayList<>();
        int bestMoveValue = currentState.getValue();
        for (State state : currentState.getChildren()) {
            if (state.getValue() == bestMoveValue)
                bestMoves.add(state.getLastMove());
        }
        return bestMoves.toArray(new Move[]{});
    }

    /**
     * Generate the game tree with root s of depth d.
     * The game tree's nodes are State objects that represent the state of a game
     * and whose children are all possible States that can result from the next move.
     * <p/>
     * NOTE: this method runs in exponential time with respect to d.
     * With d around 5 or 6, it is extremely slow and will start to take a very
     * long time to run.
     * <p/>
     * Note: If s has a winner (four in a row), it should be a leaf.
     */
    public static void createGameTree(State s, int d) {
        if (d == 0) return;
        s.initializeChildren();
        for (State child : s.getChildren()) {
            createGameTree(child, d - 1);
        }
    }

    /**
     * Call minimax in ai with state s.
     */
    public static void minimax(AI ai, State s) {
        ai.minimax(s);
    }

    /**
     * State s is a node of a game tree (i.e. the current State of the game).
     * Use the Minimax algorithm to assign a numerical value to each State of the
     * tree rooted at s, indicating how desirable that java.State is to this player.
     */
    public void minimax(State s) {
        State[] children = s.getChildren();
        // If we have a leaf node then evaluate the board at this point.
        if (children.length == 0) {
            s.setValue(evaluateBoard(s.getBoard()));
            return;
        }
        // Otherwise evaluate the children to derive this node's value.
        int value;
        if (s.getPlayer() == player) {
            // If we are playing find the maximum value.
            value = Integer.MIN_VALUE;
            for (State child : children) {
                minimax(child);
                if (child.getValue() > value) value = child.getValue();
            }
        } else {
            // Otherwise find the minimum value.
            value = Integer.MAX_VALUE;
            for (State child : children) {
                minimax(child);
                if (child.getValue() < value) value = child.getValue();
            }
        }
        // Set the value we derived.
        s.setValue(value);
    }

    /**
     * Evaluate the desirability of Board b for this player
     * Precondition: b is a leaf node of the game tree (because that is most
     * effective when looking several moves into the future).
     */
    public int evaluateBoard(Board b) {
        Player winner = b.hasConnectFour();
        int value = 0;
        if (winner == null) {
            // Store in sum the value of board b. 
            List<Player[]> locs = b.winLocations();
            for (Player[] loc : locs) {
                for (Player p : loc) {
                    value += (p == player ? 1 : p != null ? -1 : 0);
                }
            }
        } else {
            // There is a winner
            int numEmpty = 0;
            for (int r = 0; r < Board.NUM_ROWS; r = r + 1) {
                for (int c = 0; c < Board.NUM_COLS; c = c + 1) {
                    if (b.getTile(r, c) == null) numEmpty += 1;
                }
            }
            value = (winner == player ? 1 : -1) * 10000 * numEmpty;
        }
        return value;
    }
}
