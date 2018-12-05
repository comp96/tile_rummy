package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Much of the code for this class was provided by professor Michael Jason
 * Hinek, for the offering of COMP 1406 during summer of 2017
 */
public class Stock {

    private final static int STOCK_SIZE = 140;

    private Tile[] source_stock;
    private Stack<Tile> actual_stock;
    private ArrayList<Tile> drawn_tiles;

    public Stock() {
        this.source_stock = new Tile[STOCK_SIZE];
        this.actual_stock = new Stack<>();
        this.drawn_tiles = new ArrayList<>();
        make_stock(this.actual_stock);
    }

    /**
     * @return stock with all of the game's tiles
     */
    public Stack<Tile> get_stock() {
        return this.actual_stock;
    }

    /**
     * @return played tiles
     */
    public ArrayList<Tile> get_drawn_tiles() {
        return this.drawn_tiles;
    }

    /**
     * **********
     * WRAPPERS * **********
     */
    /**
     * @return tile popped
     */
    public Tile pop() {
        return this.actual_stock.pop();
    }

    /**
     * @return tile on top of stock
     */
    public Tile peek() {
        return this.actual_stock.peek();
    }

    /**
     * @return true if there are no more tiles to draw from this stock
     */
    public boolean is_Empty() {
        return this.actual_stock.isEmpty();
    }

    /**
     * Fill the source stock with tiles
     */
    public void populate_source_stock() {
        int index = 0;

        for (int r = 0; r < 14; r += 1) {
            for (int s = 0; s <= 4; s += 1) {
                this.source_stock[index++] = new Tile(Tile.COLORS[s], Tile.RANKS[r]);
                this.source_stock[index++] = new Tile(Tile.COLORS[s], Tile.RANKS[r]);
            }
        }
    }

    /**
     * Shuffle the source stock
     */
    public void shuffle_source_stock() {
        Random rnd = new Random();
        Tile swap;

        for (int i = STOCK_SIZE - 1; i >= 0; i = i - 1) {
            int pos = rnd.nextInt(i + 1);
            swap = this.source_stock[pos];
            this.source_stock[pos] = this.source_stock[i];
            this.source_stock[i] = swap;
        }
    }

    /**
     * Populate and shuffle source stock, then push tiles to actual stock
     *
     * @param stock_to_make
     */
    private void make_stock(Stack<Tile> stock_to_make) {
        this.populate_source_stock();
        this.shuffle_source_stock();

        for (int i = 0; i < STOCK_SIZE; ++i) {
            stock_to_make.push(this.source_stock[i]);
        }
    }

}
