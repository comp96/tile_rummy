package core;

import java.util.HashMap;

/**
 * Much of the code for this class was provided by professor Michael Jason
 * Hinek, for the offering of COMP 1406 during summer of 2017
 */
public class Tile {

    protected final static String[] COLORS = {"R", "B", "G", "O", "None"};
    protected final static String[] RANKS = {"None", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

    private String color;
    private String rank;
    private HashMap<String, Integer> rank_value;

    /**
     * Creates a tile with specified color and rank
     *
     * @param color is the color of the tile (must be a string from tile.COLORS)
     * @param rank is the rank of the tile (must be a string from tile.RANKS)
     */
    public Tile(String color, String rank) {
        this.color = color;
        this.rank = rank;
        this.rank_value = new HashMap<>(14);

        for (int r = 1; r < RANKS.length; r += 1) {
            this.rank_value.put(RANKS[r], r);
        }
    }

    /**
     * @return the color of this tile (must be a string from tile.colors)
     */
    public String get_color() {
        return this.color;
    }

    /**
     * Ranks have the numerical values 1 = 1, 2 = 2, 3 = 3, ..., 13 = 13
     *
     * @return the numerical rank of this tile
     */
    public int get_rank() {
        if (this.rank.equals(RANKS[0])) {
            return -1;
        } // "no tile"
        return this.rank_value.get(this.rank);
    }

    /**
     * @return the string representation of the rank of this tile (must be a
     * string from tile.RANKS)
     */
    public String get_rank_string() {
        return this.rank;
    }

    /**
     * @param tile
     * @return true if two tiles being compared have the same rank
     */
    public boolean same_rank(Tile tile) {
        return this.get_rank() == tile.get_rank();
    }

    /**
     * @param tile
     * @return true if two tiles being compared have the same color
     */
    public boolean same_color(Tile tile) {
        return this.color.equals(tile.color);
    }

    /**
     * @param tile
     * @return true if this is one rank greater and same color as another tile
     */
    public boolean ascending_rank_same_color(Tile tile) {
        return this.same_color(tile) && (this.get_rank() - tile.get_rank() == 1);
    }

    /**
     * Outputs tile's rank and color as a string
     *
     * @return String
     */
    @Override
    public final String toString() {
        if (this.rank.equals(RANKS[0])) {
            return "no tile";
        }

        int r = get_rank();

        if (r > 0 && r < 14) {
            return get_color().substring(0, 1) + r;
        } else {
            return "no tile";
        }
    }

}
