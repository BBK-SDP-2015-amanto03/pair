
/**
 * The two opposing players. A null Player value in board
 * indicates an empty tile.
 */
public enum Player {
    RED, YELLOW;

    /**
     * Return the opponent of this player.
     */
    public Player opponent() {
        return this == RED ? YELLOW : RED;
    }

    /**
     * Return the Player as a String
     */
    @Override
    public String toString() {
        return this == RED ? "RED" : "YELLOW";
    }
}
