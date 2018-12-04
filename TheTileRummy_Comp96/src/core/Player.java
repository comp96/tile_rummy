package core;

import java.util.ArrayList;
import core.Interfaces.*;

public class Player implements Observer, Subject {

    protected static int player_id = 0; // e.g. player_1, 2, 3

    private String name;
    private Hand hand;
    private ArrayList<Observer> observers;
    private int max_hand_offset;
    private int melds_played;

    public Player(String name) {
        player_id++;

        this.name = name;
        this.hand = new Hand();
        this.observers = new ArrayList<>();
        this.max_hand_offset = 0;
        this.melds_played = 0;
    }

    /**
     * @return this player's hand
     */
    public Hand get_hand() {
        return this.hand;
    }

    /**
     * @return this player's hand
     */
    public Meld get_hand_meld() {
        return this.hand.get_hand_meld();
    }

    /**
     * @return the number of melds this player has played
     */
    public int get_melds_played() {
        return this.melds_played;
    }

    /**
     * @return the name of this player
     */
    public String get_name() {
        return this.name;
    }

    /**
     * @return this player's max_hand_offset
     */
    public int get_max_hand_offset() {
        return this.max_hand_offset;
    }

    /**
     * @param set_to is the value this player's max_hand_offset will be set to
     */
    public void set_max_hand_offset(int set_to) {
        this.max_hand_offset = set_to;
    }

    /**
     * @param set_to
     */
    public void set_melds_played(int set_to) {
        this.melds_played = set_to;
    }

    /**
     * Add method to arbitrarily add tiles to this player's hand To be used for
     * testing purposes
     *
     * @param tile
     * @param stock
     */
    public void add(Tile tile, Stock stock) {
        this.hand.add(tile);
        stock.get_drawn_tiles().add(tile);
        stock.get_stock().remove(tile);
    }

    /**
     * If player has tiles in hand that can be played to melds on board, then
     * play tiles into those melds
     *
     * @param board to play tiles to
     */
    public void add_tiles_to_board_melds(Board board) {
        for (int i = 0; i < this.hand.size(); i++) {
            for (int p = 0; p < board.size(); p++) {
                board.get_meld(p).sort_meld();

                if (addable_group_tile(board.get_meld(p), this.hand.get(i))) {
                    System.out.println("group hit at: " + p);
                    //add_tile_to_board_group(board,p,this.hand.get(i));
                    //play_tile(board.get_meld(p),this.hand.get(i));
                    //board.get_meld(p).add(this.hand.get(i));
                }

                if (addable_run_tile(board.get_meld(p), this.hand.get(i))) {
                    System.out.println("run hit at: " + p);
                    //board.get_meld(p).add(this.hand.get(i));
                    //play_tile(board.get_meld(p),this.hand.get(i));
                }
            }
        }
    }

    /**
     * function to play the meld using the board tiles
     *
     * @param board to be used
     */
    public void play_board_meld(Board board) {
        for (Tile tile : hand.get_hand_meld().get_tiles()) {
            for (Meld meld : board.get_board()) {
                if (meld.is_group()) {
                    if (can_be_added_to_group(tile, meld)) {
                        meld.get_tiles().add(tile);
                    }
                } else {
                    int result = can_be_added_to_run(tile, meld);
                    if (result == 1) {
                        meld.get_tiles().add(0, tile);
                    } else if (result == 2) {
                        meld.get_tiles().add(tile);
                    }
                }
            }
        }

    }

    /**
     * Play a meld from this player's hand
     *
     * @param meld_index
     * @param board
     * @param stock
     * @param group_or_run
     */
    public void play_meld_at_index(int meld_index, Board board, Stock stock, ArrayList<Meld> group_or_run) {
        Meld temp = new Meld();

        //copy of the meld a player can play 
        for (int i = 0; i < group_or_run.get(meld_index).size(); ++i) {
            temp.add(stock.get_drawn_tiles().get(stock.get_drawn_tiles().indexOf(group_or_run.get(meld_index).get_tiles().get(i))));
        }

        board.add(temp);

        for (int j = group_or_run.get(meld_index).size() - 1; j >= 0; --j) {
            this.hand.get_hand_meld().get_tiles().remove(group_or_run.get(meld_index).get_tiles().remove(j));
        }

        this.melds_played += 1;
        this.get_hand().delete_empty_meld(group_or_run);
    }

    /**
     * Helper function for play_meld
     *
     * @param meld
     * @param board
     * @param stock
     * @param group_or_run
     */
    private void play_meld_helper(Meld meld, Board board, Stock stock, ArrayList<Meld> group_or_run) {
        Meld temp = new Meld();
        int meld_index;

        if (group_or_run.contains(meld)) {
            meld_index = group_or_run.indexOf(meld);

            for (int i = 0; i < group_or_run.get(meld_index).size(); ++i) {
                temp.add(stock.get_drawn_tiles().get(stock.get_drawn_tiles().indexOf(group_or_run.get(meld_index).get_tiles().get(i))));
            }

            board.add(temp);

            for (int j = group_or_run.get(meld_index).size() - 1; j >= 0; --j) {
                this.hand.get_hand_meld().get_tiles().remove(group_or_run.get(meld_index).get_tiles().remove(j));
            }

            this.melds_played += 1;
            this.get_hand().delete_empty_meld(group_or_run);
        } else {
            return;
        }
    }

    /**
     * Play a meld from this player's hand
     *
     * @param meld
     * @param board
     * @param stock
     */
    public void play_meld(Meld meld, Board board, Stock stock) {
        play_meld_helper(meld, board, stock, this.hand.get_groups());
        play_meld_helper(meld, board, stock, this.hand.get_runs());
    }

    /**
     * If this player's hand has a combination of melds that adds up to 30 or
     * more points, then play that combination of melds
     *
     * @param board
     * @param stock
     */
    public boolean play_30_plus(Board board, Stock stock) {
        if (this.hand.has_30_plus()) {
            for (int i = this.hand.get_groups().size() - 1; i >= 0; --i) {
                this.play_meld_at_index(i, board, stock, this.hand.get_groups());
            }
            for (int i = this.hand.get_runs().size() - 1; i >= 0; --i) {
                this.play_meld_at_index(i, board, stock, this.hand.get_runs());
            }
            return true;
        }

        return false;
    }

    /**
     * Play a single tile Maybe we can use tile's index
     *
     * @param board
     * @param index
     * @param tile
     */
    public void play_tile(Board board, int index, Tile tile) {

        if (board.get_meld(index).is_group()) {
            board.get_meld(index).add(tile);
            this.hand.get_hand_meld().get_tiles().remove(tile);
        }

        if (board.get_meld(index).is_run()) {
            board.get_meld(index).add(tile);
            this.hand.get_hand_meld().get_tiles().remove(tile);
        }
    }

    /**
     * Note: The if-else block in this method will prevent program from crashing
     * with an exception However, it will go into an infinite loop
     *
     * draw_times tiles from stock and put them into hand
     *
     * @param stock
     * @param draw_times
     */
    public void draw(Stock stock, int draw_times) {
        if (!stock.is_Empty()) {
            for (int i = 0; i < draw_times; ++i) {
                stock.get_drawn_tiles().add(stock.pop());
                this.hand.add(stock.get_drawn_tiles().get(stock.get_drawn_tiles().size() - 1));
            }
            this.hand.get_hand_meld().sort_meld();
        } else {
            System.out.println("Stock is empty. Player could not draw");
        }
    }

    /**
     * @return true if a player has played all the tiles in its hand
     */
    public boolean winner() {
        return this.hand.size() == 0;
    }

    /**
     * Compare this player's hand size with hand_size
     *
     * @param hand_size
     * @return difference between sizes
     */
    public int compare_hand_sizes(int hand_size) {
        return this.hand.size() - hand_size;
    }

    /**
     *
     * @param board
     */
    public void play_tiles_to_table_melds(Board board) {
        for (int i = 0; i < this.hand.size(); i++) {
            for (int p = 0; p < board.size(); p++) {
                board.get_meld(p).sort_meld();

                if (addable_group_tile(board.get_meld(p), this.hand.get(i))) {
                    play_tile(board, p, this.hand.get(i));
                }

                if (addable_run_tile(board.get_meld(p), this.hand.get(i))) {
                    play_tile(board, p, this.hand.get(i));
                }
            }
        }
    }

    /**
     * @param meld
     * @param tile
     * @return true if tile can be added to group
     */
    public boolean addable_group_tile(Meld meld, Tile tile) {
        int count = 0;

        for (int i = 0; i < meld.size(); i++) {
            if (meld.size() <= 3 && tile.same_rank(meld.get_tile(i)) && meld.is_group()) {
                if (!tile.same_color(meld.get_tile(i))) {
                    count += 1;
                }
            }
        }
        return count >= meld.size();
    }

    /**
     * @param meld
     * @param tile
     * @return true if tile can be added to run
     */
    public boolean addable_run_tile(Meld meld, Tile tile) {
        int count = 0;
        for (int i = 0; i < meld.size(); i++) {
            if (meld.is_run() && meld.get_tile(i).same_color(tile)) {
                if (!tile.same_rank(meld.get_tile(i)) && tile.get_rank() == meld.get_tile(meld.size() - 1).get_rank() + 1) {
                    count += 1;
                }
            }
        }

        return count >= meld.size();
    }

    /**
     * helper function for can run all (using board)
     *
     * @param tile1 tile to be check
     * @param meld meld in which tile has to be check
     * @return true if tile1 can be added as a run in meld
     */
    public int can_be_added_to_run(Tile tile1, Meld meld) {
        Tile first = meld.get_tile(0);
        Tile last = meld.get_tile(meld.size() - 1);

        if (first.get_rank() > tile1.get_rank()) {
            if (first.ascending_rank_same_color(tile1)) {
                return 1;
            }
        } else {
            if (tile1.ascending_rank_same_color(last)) {
                return 2;
            }
        }

        return 0;
    }

    /**
     * helper function for can run all (using board)
     *
     * @param tile1 tile to be check
     * @param meld meld in which tile has to be check
     * @return true if tile1 can be added as a run in meld
     */
    public boolean can_be_added_to_group(Tile tile1, Meld meld) {
        Tile first = meld.get_tile(0);
        return first.same_rank(tile1) && !first.same_color(tile1);
    }

    /**
     *
     * @param board to be used i function
     * @return true if player can play its all tiles (using board) else false
     */
    public boolean can_play_all(Board board) {
        int count = 0;
        for (Tile tile : this.hand.get_hand_meld().get_tiles()) {
            for (Meld meld : board.get_board()) {
                if (meld.is_group()) {
                    if (can_be_added_to_group(tile, meld)) {
                        count++;
                    }
                } else {
                    int result = can_be_added_to_run(tile, meld);
                    if (result == 1 || result == 2) {
                        count++;
                    }
                }
            }
        }

        if (count == hand.size()) { // Check if all tiles has added means user can play all tiles with table
            return true;
        } else {
            return false;
        }
    }

    // Observer
    @Override
    public void register(Observer new_observer) {
        this.observers.add(new_observer);
    }

    @Override
    public void unregister(Observer delete_observer) {
        int observer_index = this.observers.indexOf(delete_observer);
        System.out.println("Observer " + (observer_index + 1) + " has been deleted");
        this.observers.remove(observer_index);
    }

    @Override
    public void notify_observers() {
        for (Observer observer : this.observers) {
            observer.update(this.get_hand().size());
        }
    }

    // Subject
    @Override
    public void update(int hand_size) {//p1 hand size p2
        int temp = this.compare_hand_sizes(hand_size);
        if (temp > this.max_hand_offset) {
            this.max_hand_offset = temp;
        }
    }

    /**
     * return this player's hand
     * @return 
     */
    public String print_hand() {
        return this.name + "s hand is: " + this.hand.hand_to_string();
    }

    /**
     * Print this player's hand and melds in it
     * @return 
     */
    public String print_hand_and_melds() {
        return this.print_hand()+"\n"+this.hand.print_melds();

    }

}
