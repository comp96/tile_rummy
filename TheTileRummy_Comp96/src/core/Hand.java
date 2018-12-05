package core;

import java.util.ArrayList;

/**
 * Note: Once we start taking into account the tiles on the table, a player's
 * meld will not be limited to the contents of its hand
 *
 * @author Comp96
 */
public class Hand {

    private Meld hand_meld;
    private ArrayList<Meld> groups;
    private ArrayList<Meld> runs;

    public Hand() {
        this.hand_meld = new Meld();
        this.groups = new ArrayList<>();
        this.runs = new ArrayList<>();
    }

    /**
     * @param i
     * @return tile at index i
     */
    public Tile get(int i) {
        return this.hand_meld.get_tiles().get(i);
    }

    /**
     * @return an ArrayList of Melds with all the groups in this hand
     */
    public ArrayList<Meld> get_groups() {
        return this.groups;
    }

    /**
     * @return the ArrayList of Tiles that stores the tiles in this hand
     */
    public Meld get_hand_meld() {
        return this.hand_meld;
    }

    /**
     * @return this hand's runs
     */
    public ArrayList<Meld> get_runs() {
        return this.runs;
    }

    /**
     * @param group_or_run
     * @return the index of the meld with the highest score in the ArrayList of
     * melds passed in as an argument
     */
    public int get_best_meld_index(ArrayList<Meld> group_or_run) {
        int max_index = 0;

        for (int i = group_or_run.size() - 1; i > 0; --i) {
            if (group_or_run.get(i).get_score() > group_or_run.get(i - 1).get_score()) {
                max_index = i;
            }
        }

        return max_index;
    }

    /**
     * @return the index of the meld with the highest score in the ArrayList of
     * melds passed in as an argument
     */
    public Meld get_best_meld() {
        int group_index = 0;
        int max_group_score = 0;
        int run_index = 0;
        int max_run_score = 0;

        if (!this.groups.isEmpty()) {
            group_index = get_best_meld_index(this.groups);
            max_group_score = this.groups.get(group_index).get_score();
        }

        if (!this.runs.isEmpty()) {
            run_index = get_best_meld_index(this.runs);
            max_run_score = this.runs.get(run_index).get_score();
        }

        if (max_group_score > max_run_score) {
            return this.groups.get(group_index);
        } else {
            return this.runs.get(run_index);
        }
    }

    /**
     * Add one tile to hand
     *
     * @param tile
     */
    public void add(Tile tile) {
        this.hand_meld.add(tile);
    }

    /**
     * @return this hand's size
     */
    public int size() {
        return this.hand_meld.size();
    }

    /**
     * Helper function for find_groups and find_runs Delete groups_or_runs' last
     * meld if it is empty
     *
     * @param groups_or_runs
     */
    public void delete_empty_meld(ArrayList<Meld> groups_or_runs) {
        for (int i = 0; i < groups_or_runs.size(); ++i) {
            if (groups_or_runs.get(i).get_tiles().isEmpty()) {
                groups_or_runs.remove(i);
            }
        }
    }

    /**
     * Find groups in hand and put them in this.groups
     */
    public void find_groups() {
        int meld_index = 0;
        this.groups.clear();
        this.groups.add(new Meld());

        for (int i = 0; i < this.hand_meld.size(); ++i) {
            for (int j = i + 1; j < this.hand_meld.size(); ++j) {	// Compare tile i with the rest of the tiles in hand
                if (this.hand_meld.get_tiles().get(i).same_rank(this.hand_meld.get_tiles().get(j))
                        && !this.hand_meld.get_tiles().get(i).same_color(this.hand_meld.get_tiles().get(j))) {
                    this.groups.get(meld_index).add(this.hand_meld.get_tiles().get(j));
                }
            } // At this point we should have a group to work with
            possible_group:
            if (this.groups.get(meld_index).size() > 1) { // should be > 2 but for some reason it only works with > 1
                for (int k = 0; k < meld_index; ++k) {
                    if (this.groups.get(k).get_tiles().get(0).same_rank(this.hand_meld.get_tiles().get(i))) { 	// If there already is a group of same rank as tile i within this.groups, then delete the current group since it is already accounted for
                        this.groups.get(meld_index).get_tiles().clear();
                        break possible_group;
                    }
                }

                this.groups.get(meld_index).add(this.hand_meld.get_tiles().get(i));
                this.groups.get(meld_index).remove_duplicates();

                // After removing duplicates, if current group's size is 2, discard group
                if (this.groups.get(meld_index).size() == 2) {
                    this.groups.get(meld_index).get_tiles().clear();
                    break possible_group;
                }

                meld_index++;
                this.groups.add(new Meld());
            } else {
                this.groups.get(meld_index).get_tiles().clear();
            }
        }

        delete_empty_meld(this.groups);
    }

    /**
     * Helper function for find_runs()
     *
     * @param meld_index
     * @param i
     */
    private void add_to_run(int meld_index, int i) {
        if (!this.runs.get(meld_index).get_tiles().contains(this.hand_meld.get_tiles().get(i))) {
            this.runs.get(meld_index).add(this.hand_meld.get_tiles().get(i));
        }
    }

    /**
     * Find all the runs in this (sorted) hand and store them in this.runs
     */
    public void find_runs() {
        int meld_index = 0;
        this.runs.clear();
        this.runs.add(new Meld());

        for (int i = this.hand_meld.size() - 1; i > 0; --i) {
            if (this.hand_meld.get_tiles().get(i).ascending_rank_same_color(this.hand_meld.get_tiles().get(i - 1))) {
                this.add_to_run(meld_index, i);
                this.add_to_run(meld_index, i - 1);
                if (i != 1) {
                    continue;
                }
            }
            if (this.runs.get(meld_index).size() > 2) { // find groups is > 1 but here it's 2, not sure why
                meld_index++;
                this.runs.add(new Meld());
            } else {
                this.runs.get(meld_index).get_tiles().clear();
            }
        }

        delete_empty_meld(this.runs);
    }

    /**
     * Find all melds (groups and runs) in this hand
     */
    public void find_melds() {
        this.find_groups();
        this.find_runs();
    }

    /**
     * @return true if there is at least one group in this hand
     */
    public boolean has_groups() {
        this.find_groups();
        return this.groups.size() > 0;
    }

    /**
     * @return true if there is at least one run in this hand
     */
    public boolean has_runs() {
        this.find_runs();
        return this.runs.size() > 0;
    }

    /**
     * Call has_runs() and has_groups() In turn those call find_runs() and
     * find_groups()
     *
     * @return true if there is at least one meld in this hand
     */
    public boolean has_melds() {
        return this.has_groups() || this.has_runs();
    }

    /**
     * @return true if this hand has a combination of melds that adds up to 30
     * or more points
     */
    public boolean has_30_plus() {
        this.find_melds();
        this.remove_meld_shared_tile();

        int score = 0;

        for (int i = 0; i < this.groups.size(); ++i) {
            score += this.groups.get(i).get_score();
        }
        for (int i = 0; i < this.runs.size(); ++i) {
            score += this.runs.get(i).get_score();
        }

        return score > 29;
    }

    /**
     * Adds tile to hand and delete from meld
     *
     * @param meld
     */
    public void add_meld_and_empty_it(Meld meld) {
        for (int i = meld.size() - 1; i >= 0; --i) {
            this.hand_meld.add(meld.remove(i));
        }
    }

    /**
     * Remove smallest meld if a tile is in both a group and a run One tile can
     * only be in up to two melds at a time (group and run) I can't think of an
     * scenario where a tile would be in three different melds
     *
     * e.g. { [B1] [O1] [R1] } and { [03] [O2] [1] } In the example above, the
     * group of ones would be removed
     */
    public void remove_meld_shared_tile() {
        loop_i:
        for (int i = this.groups.size() - 1; i >= 0; --i) { // Go through this hand's groups
            for (int j = this.runs.size() - 1; j >= 0; --j) { // Go through this hand's runs
                for (Tile group_tile : this.groups.get(i).get_tiles()) { // Go through group i's tiles
                    for (Tile run_tile : this.runs.get(j).get_tiles()) { // Go through run j's tiles
                        if (group_tile.hashCode() == run_tile.hashCode()) { // Compare tile's address in memory
                            if (this.groups.get(i).get_score() <= this.runs.get(j).get_score()) { // Choose group or run with highest score and keep it
                                this.groups.remove(i);
                                continue loop_i;
                            } else {
                                this.runs.remove(j);
                                continue loop_i;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Textual interface output to show the first "number_of_tiles" in hand
     *
     * @param number_of_tiles
     */
    public void print_tiles(int number_of_tiles) {
        System.out.print("First " + number_of_tiles + " tiles in hand are: ");
        for (int i = 0; i < number_of_tiles; ++i) {
            System.out.print(this.hand_meld.get_tiles().get(i).toString() + " ");
        }
        System.out.println();
    }

    /**
     * Print hand to console
     */
    public void print_hand() {
        System.out.print("Full hand is: ");
        for (int i = 0; i < this.hand_meld.size(); ++i) {
            System.out.print(this.hand_meld.get_tiles().get(i).toString() + " ");
        }
        System.out.println();
    }

    /**
     * Print all groups in this.groups
     *
     * @return
     */
    public String print_groups() {
        String str = "";
        for (int i = 0; i < this.groups.size(); ++i) {
            str += i + ": " + this.groups.get(i).print_meld();
        }
        return str;
    }

    /**
     * Print all runs in this.runs
     *
     * @return
     */
    public String print_runs() {
        String str = "";
        for (int i = 0; i < this.runs.size(); ++i) {
            str += i + ": " + this.runs.get(i).print_meld();
        }
        return str;
    }

    /**
     * Print all groups and runs in this hand
     *
     * @return
     */
    public String print_melds() {
        String str = "";
        if (this.groups.size() > 0) {
            str += "Group(s): ";
            str += this.print_groups();
        }
        if (this.runs.size() > 0) {
            str += "Run(s): ";
            str += this.print_runs();
        }
        return str;
    }

    /**
     * Convert hand to a string that can be printed in the textual interface
     *
     * I got some of this code from StackOverflow
     * https://stackoverflow.com/questions/5925420/how-to-create-a-string-from-string-array-or-arraylist
     *
     * @return string
     */
    public final String hand_to_string() {
        int hand_size = this.hand_meld.size();
        String[] tiles_array = new String[hand_size];
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < hand_size; ++i) {
            tiles_array[i] = this.hand_meld.get_tiles().get(i).toString();
        }

        for (String string : tiles_array) {
            if (builder.length() > 0) {
                builder.append(",  ");
            }
            builder.append(string);
        }

        return builder.toString();
    }

}
