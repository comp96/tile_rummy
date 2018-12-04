package core;

import java.util.ArrayList;
import java.util.Collections;

public class Meld {

    /**
     * **********************
     * INSTANCE VARIABLE(S) *
	 ***********************
     */
    private ArrayList<Tile> tiles;
    private int score;

    /**
     * *************
     * CONSTRUCTOR *
	 **************
     */
    public Meld() {
        this.tiles = new ArrayList<>();
        this.score = 0;
    }

    /**
     * ***********
     * GETTER(S) *
	 ************
     */
    /**
     * @return this meld
     */
    public ArrayList<Tile> get_tiles() {
        return this.tiles;
    }

    /**
     * @param index
     * @return tile at index i
     */
    public Tile get_tile(int index) {
        return this.tiles.get(index);
    }

    /**
     * @return this meld's score (after being calculated within this method)
     */
    public int get_score() {
        this.score = 0;
        for (int i = 0; i < this.tiles.size(); ++i) {
            this.score += this.tiles.get(i).get_rank();
        }
        return this.score;
    }

    /**
     * **********
     * WRAPPERS *
	 ***********
     */
    /**
     * Add tile to meld
     *
     * @param tile
     */
    public void add(Tile tile) {
        this.tiles.add(tile);
    }

    /**
     * @param tile to be removed
     */
    public void remove(Tile tile) {
        for (int i = 0; i < size(); i++) {
            if (tile.same_color(this.get_tile(i)) && tile.same_rank(this.get_tile(i))) {
                remove(i);
            }
        }
    }

    /**
     * Remove tile from this meld
     *
     * @param i
     * @return removed tile
     */
    public Tile remove(int i) {
        return this.tiles.remove(i);
    }

    /**
     * @return the size of this meld
     */
    public int size() {
        return this.tiles.size();
    }

    /**
     * ******
     * ELSE *
	 *******
     */
    /**
     * Remove duplicate tiles in this meld
     */
    public void remove_duplicates() {
        for (int i = 0; i < this.tiles.size(); ++i) {
            for (int j = i + 1; j < this.tiles.size(); ++j) {
                if (this.tiles.get(i).same_color(this.tiles.get(j))
                        && this.tiles.get(i).same_rank(this.tiles.get(j))) {
                    this.tiles.remove(j);
                }
            }
        }
    }

    /**
     * @return true if this meld is a run
     */
    public boolean is_run() {
        if (this.get_tile(1).ascending_rank_same_color(this.get_tile(0))) {
            return true;
        }
        return false;
    }

    /**
     * @return true if this meld is a group
     */
    public boolean is_group() {
        if (this.get_tile(1).same_rank(this.get_tile(0))) {
            return true;
        }
        return false;
    }

    /**
     * Bubble sort algorithm to sort the list of tiles in ascending order
     *
     * @param tiles_list the list of tiles to be sorted
     * @return a sorted list
     */
    public ArrayList<Tile> sort_rank(ArrayList<Tile> tiles_list) {
        for (int i = 0; i < tiles_list.size(); i++) {
            for (int j = 0; j < tiles_list.size() - 1; j++) {
                if (tiles_list.get(j).get_rank() >= tiles_list.get(j + 1).get_rank()) {
                    Collections.swap(tiles_list, j, j + 1);
                }
            }
        }
        return tiles_list;
    }

    /**
     * Sort the tiles of player for each color i.e Sorting the list "hand" by
     * using bubble sort for each color
     */
    public void sort_meld() {
        ArrayList<Tile> red = new ArrayList<>(), blue = new ArrayList<>(), green = new ArrayList<>(), orange = new ArrayList<>();

        for (Tile tile : this.tiles) {
            switch (tile.get_color()) {
                case "R":
                    red.add(tile);
                    break;
                case "B":
                    blue.add(tile);
                    break;
                case "G":
                    green.add(tile);
                    break;
                case "O":
                    orange.add(tile);
                    break;
            }
        }

        // Now sorting each set
        red = sort_rank(red);
        blue = sort_rank(blue);
        green = sort_rank(green);
        orange = sort_rank(orange);

        this.tiles = new ArrayList<>();
        this.tiles.addAll(red);
        this.tiles.addAll(blue);
        this.tiles.addAll(green);
        this.tiles.addAll(orange);
    }

    /**
     * *******
     * PRINT *
	 ********
     */
    /**
     * Print meld to screen
     */
    public void print_meld() {
        System.out.print("{ ");
        for (int i = 0; i < this.tiles.size(); i++) {
            System.out.print("[" + this.tiles.get(i).toString() + "] ");
        }
        System.out.print("}\n");
    }

}
