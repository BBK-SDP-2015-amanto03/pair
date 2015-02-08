
import java.util.Random;

/**
 * <p>
 * NOTHING FOR YOU TO DO HERE.
 * <p/>
 * A Solver that chooses moves randomly.
 */
public class Dummy implements Solver {

    private Player myColour;

    public Dummy(Player colour) {
        myColour = colour;
    }

    /**
     * See Solver.getMoves for the specification.
     */
    @Override
    public Move[] getMoves(Board b) {
        Random rand = new Random();
        int column = rand.nextInt(Board.NUM_COLS);
        while (b.getTile(0, column) != null) {
            column = rand.nextInt(Board.NUM_COLS);
        }
        Move[] move = {new Move(myColour, column)};
        return move;
    }

}
