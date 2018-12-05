package core;

import java.util.ArrayList;

/**
 * @author Luis, David, Heba, Alex
 */
public class Board {

    private ArrayList<Meld> board;

    public Board() {
        this.board = new ArrayList<>();
    }

    /**
     * Add a meld to the board
     *
     * @param meld
     */
    public void add(Meld meld) {
        this.board.add(meld);
    }

    /**
     * @return true if there are no melds on this board
     */
    public boolean is_empty() {
        return this.board.isEmpty();
    }

    /**
     * @return the number of melds on this board
     */
    public int size() {
        return this.board.size();
    }

    /**
     * @return the ArrayList of Melds that is stored on the board
     */
    public ArrayList<Meld> get_board() {
        return this.board;
    }

    /**
     * @param i
     * @return meld at position i
     */
    public Meld get_meld(int i) {
        return this.board.get(i);
    }

    /**
     * Print to screen all melds on the board
     *
     * @return
     */
    public String print_board() {
        String str = "";
        str += "Melds on board are: ";
        for (int i = 0; i < this.board.size(); i++) {
            str += "\n" + this.board.get(i).print_meld();
        }
        return str;
    }

    /**
     * Print the first num_melds in board
     *
     * @param index
     */
    public void print_melds_up_to_index(int index) {
        for (int i = 0; i < index; i++) {
            this.board.get(i).print_meld();
        }
    }

    /**
     * Print melds up to index
     *
     * @param index
     */
    public void print_melds_from_index(int index) {
        for (int i = index; i < this.board.size(); i++) {
            System.out.print("*");
            this.board.get(i).print_meld();
        }
    }
}
