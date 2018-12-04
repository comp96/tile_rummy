package core;

import java.util.Scanner;

/**
 * We are going to use the same textual interface methods in Play_Test and Game
 * This class has been created to avoid copy-and-paste methods in the
 * aforementioned two classes
 */
public class Text_UI {

    /**
     * *******************
     * CLASS VARIABLE(S) *
	 ********************
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * ***********
     * GETTER(S) *
	 ************
     */
    /**
     * @return scanner
     */
    public Scanner get_scanner() {
        return this.scanner;
    }

    /**
     * *******
     * PRINT *
	 ********
     */
    /**
     * Print a string
     *
     * @param string to be printed
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Print a blank line
     */
    public void print_blank_line() {
        print("");
    }

    /**
     * Print a divider line
     */
    public void print_divider() {
        print("=== === === === ==== === === === ===");
        print_blank_line();
    }

    /**
     * Print a welcome message
     */
    public void print_welcome() {
        print("Hello, welcome to Rummikub");
        print_divider();
    }

    /**
     * Print a message with the name of the test
     *
     * @param test_name
     */
    public void print_test_name(String test_name) {
        print_blank_line();
        print(test_name);
        print_divider();
    }

    /**
     * Start of turn message(s)
     *
     * @param player
     */
    public void print_turn_start(Player player) {
        print(player.get_name() + "'s turn has started");
        print(player.get_name() + "'s hand is: " + player.get_hand().hand_to_string() + "\n");
    }

    /**
     * Show the hand of player whose turn just ended Show all the melds on the
     * board
     *
     * @param player
     * @param board
     * @param original_board_size
     */
    public void print_turn_end(Player player, Board board, int original_board_size) {
        print_board(board, original_board_size);
        print(player.get_name() + "'s turn has ended");
        print(player.get_name() + "'s hand is: " + player.get_hand().hand_to_string());
        print_divider();
    }

    /**
     * Invalid input
     */
    public void print_invalid_input() {
        print("Invalid input. Please try again");
    }

    /**
     * Print board
     *
     * @param board
     * @param original_board_size
     */
    public void print_board(Board board, int original_board_size) {
        if (board.is_empty()) {
            print("\nBoard is empty\n");
            return;
        }
        print("\nBoard is");
        board.print_melds_up_to_index(original_board_size);
        board.print_melds_from_index(original_board_size);
        print("");
    }

    /**
     * Print tile drawn by player
     *
     * @param player
     * @param stock
     */
    public void print_tile_drawn(Player player, Stock stock) {
        System.out.println(player.get_name() + " drew tile " + stock.get_drawn_tiles().get(stock.get_drawn_tiles().size() - 1));
    }

    /**
     * Print meld played notification
     *
     * @param player
     */
    public void print_played_meld(Player player) {
        print(player.get_name() + " played meld");
    }

    /**
     * Print value of player's max_hand_offset()
     *
     * @param player
     */
    public void print_max_hand_offset(Player player) {
        print(player.get_name() + "'s max_hand_offset is: " + player.get_max_hand_offset());
        print_divider();
    }

    /**
     * Placeholder message
     *
     * @param player
     */
    public void print_board_reuse(Player player) {
        print(player.get_name() + " would reuse table");
    }

    /**
     * @param player who just played its first meld(s)
     */
    public void print_played_first_meld(Player player) {
        print(player.get_name() + " played its first meld(s)");
    }

    /**
     * @param player who just played as many tiles/melds as possible
     */
    public void print_played_as_many_ap(Player player) {
        print(player.get_name() + " played as many tiles/melds as possible");
    }

    /**
     * @param player who did not have 30+ points meld(s) for first play
     */
    public void print_not_enough_points(Player player) {
        print(player.get_name() + " did not have a 30+ combination");
    }

    /**
     * @param player who must wait
     */
    public void print_has_melds_must_wait(Player player) {
        print(player.get_name() + " has a 30+, but must wait");
    }

    /**
     * @param player who emptied its hand
     */
    public void print_played_all(Player player) {
        print(player.get_name() + " played all its tiles/melds");
    }
}
