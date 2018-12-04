package core;

import java.util.ArrayList;

/**
 * @author Luis, David, Heba, Alex
 */
public class Board {

    /**
     * **********************
     * INSTANCE VARIABLE(S) *
	 ***********************
     */
    private ArrayList<Meld> board;

    /**
     * *************
     * CONSTRUCTOR *
	 **************
     */
    public Board() {
        this.board = new ArrayList<>();
    }

    /**
     * **********
     * WRAPPERS *
	 ***********
     */
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
     * ***********
     * GETTER(S) *
	 ************
     */
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
     * *******
     * PRINT *
	 ********
     */
    /**
     * Print to screen all melds on the board
     */
    public void print_board() {
        System.out.println("Melds on board are: ");
        for (int i = 0; i < this.board.size(); i++) {
            this.board.get(i).print_meld();
        }
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
