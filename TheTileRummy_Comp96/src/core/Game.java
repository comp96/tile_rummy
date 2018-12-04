package core;

import java.util.ArrayList;
import core.Interfaces.*;
import core.Strategies.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author Luis, Heba, Alex
 */
public class Game {

    /**
     * CLASS VARIABLE(S) 
     */
    private final static int initial_hand_size = 14;	// Initial hand size will always be the same for all Games
    private final static int winning_hand_size = 0;
    private static Text_UI text = new Text_UI();

    private static int choice_int;
    private static String choice_str;

    private static Strategy strategy_1 = new Strategy_1();
    private static Strategy strategy_2 = new Strategy_2();
    private static Strategy strategy_3 = new Strategy_3();

    /**
     * **********************
     * INSTANCE VARIABLE(S) * **********************
     */
    private Board board; // if this.board.get_size() == 0, then no melds have been played
    private Stock stock;
    private boolean end_game;

    private int turn;
    private int round;

    private Human_Player player_h;
    private AI_Player player_1;
    private AI_Player player_2;
    private AI_Player player_3;

    private ArrayList<Player> players;

    /**
     * *************
     * CONSTRUCTOR * *************
     */
    public Game() {
        this.board = new Board();
        this.stock = new Stock();
        this.end_game = false;

        this.turn = 0;
        this.round = 0;

        this.player_h = new Human_Player("player_h");
        this.player_1 = new AI_Player(strategy_1);
        this.player_2 = new AI_Player(strategy_2);
        this.player_3 = new AI_Player(strategy_3);

        this.players = new ArrayList<>();
        this.players.add(player_h);
        this.players.add(player_1);
        this.players.add(player_2);
        this.players.add(player_3);

        for (int i = 0; i < this.players.size(); ++i) {
            if (!this.players.get(i).get_name().equals("player_3")) {
                this.players.get(i).register(player_3);
            }
        }
    }

    /**
     * ******
     * ELSE * ******
     */
    /**
     * Set up game
     */
    public void set_up() {
        text.print("Setup: ");

        for (int i = 0; i < this.players.size(); ++i) {
            this.players.get(i).draw(stock, initial_hand_size);
            this.players.get(i).print_hand();
        }

        text.print_divider();
    }

    /**
     * Note: The if-statement in this method ends the game if the stock is empty
     * However, the game should continue until a player has played all its
     * tiles, regardless of whether or not the stock is empty This should be
     * fixed once the code to allow players to play until the end has been
     * written
     *
     * Check if a player has zero tiles in its hand
     *
     * @param player whose hand size will be checked
     */
    public void check_end_game(Player player) {
        if (player.get_hand().size() <= winning_hand_size || this.stock.is_Empty()) {
            this.end_game = true;	// To test we can change this from == 0 to == 10
        }
    }

    /**
     * To Change Settings of the Game(players choice human or AI)
     */
    public void settings() {
        
        try{
            BufferedWriter br=new BufferedWriter(new FileWriter("settings.txt"));
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Play this game
     */
    public void play_game() {

        text.print_welcome();
        set_up();

        // As soon as a player has zero tiles at the end of its turn, end game
        while (!this.end_game) {
            player_h_turn();
            for (int i = 1; i < this.players.size(); ++i) { // Assume player_h is at index 0
                if (!this.end_game) {
                    player_n_turn();
                }
            }
        }

        for (int i = 0; i < this.players.size(); ++i) {
            if (this.players.get(i).get_hand().size() <= winning_hand_size) {
                text.print(this.players.get(i).get_name() + " has won the game");
            }
        }
    }

    /**
     *
     */
    public void play_another_meld(Player player) {
        if (player.get_hand().has_melds()) {
            text.print("Play another meld (y/n)?");
            choice_str = text.get_scanner().next();

            switch (choice_str) {
                case "y":
                    choose_group_or_run();
                    break;
                case "n":
                    break;
                default:
                    text.print_invalid_input();
                    choose_group_or_run();
                    break;
            }
        }
    }

    /**
     * Helper function for player_h_turn
     *
     * @param groups_or_runs
     */
    public void choose_meld(ArrayList<Meld> groups_or_runs) {
        int groups_or_runs_size = groups_or_runs.size() - 1;
        text.print("\nWhich meld would player_h like to play (0-" + groups_or_runs_size + "): ");
        choice_int = text.get_scanner().nextInt();

        if (choice_int <= groups_or_runs_size) {
            this.player_h.play_meld_at_index(choice_int, this.board, this.stock, groups_or_runs);
            player_h.print_hand_and_melds();
            play_another_meld(player_h);
        } else {
            text.print_invalid_input();
            choose_meld(groups_or_runs);
        }
    }

    /**
     * A second helper function for player_h_turn
     */
    public void choose_group_or_run() {
        text.print("\nWould player_h like to play a group or run (g/r): ");
        choice_str = text.get_scanner().next();

        switch (choice_str) {
            case "g":
                choose_meld(this.player_h.get_hand().get_groups());
                break;
            case "r":
                choose_meld(this.player_h.get_hand().get_runs());
                break;
            default:
                text.print_invalid_input();
                choose_group_or_run();
                break;
        }
    }

    /**
     * A third helper function for player_h_turn
     */
    public void draw_or_play() {
        text.print("\nPlease choose 'd' to draw or 'p' to play:");
        choice_str = text.get_scanner().next();

        switch (choice_str) {
            case "d":
                this.player_h.draw(this.stock, 1);
                break;
            case "p":
                choose_group_or_run();
                break;
            default:
                text.print_invalid_input();
                draw_or_play();
                return;
        }
    }

    /**
     * Fourth helper function for player_h_turn
     */
    public void player_h_play_turn() {
        if (this.player_h.get_melds_played() > 0 && this.player_h.get_hand().has_melds()) {
            this.player_h.get_hand().print_melds();
            draw_or_play();
        } else if (this.player_h.get_melds_played() == 0 && this.player_h.get_hand().has_30_plus()) {
            this.player_h.get_hand().print_melds();
            draw_or_play();
        } else {
            text.print_not_enough_points(player_h);
            this.player_h.draw(this.stock, 1);
            text.print_tile_drawn(player_h, stock);
        }
    }

    /**
     * Helper function that handles end of turn behavior
     */
    public void end_turn(Player player, int original_board_size) {
        text.print_turn_end(player, board, original_board_size);
        if (!player.get_name().equals("player_3")) {
            player.notify_observers();
        } else {
            this.player_3.set_max_hand_offset(0);
        }
        //		text.print_max_hand_offset(this.player_3); // Only here for testing purposes, meant to be deleted or commented out
        check_end_game(player);
        this.turn += 1;
    }

    /**
     * Human player's turn
     *
     * @param board on which he is playing with
     * @param stock with which he playing (stock of tiles)
     */
    public void player_h_turn() {
        int original_board_size = this.board.size();

        text.print_turn_start(player_h);
        player_h_play_turn();
        end_turn(player_h, original_board_size);
    }

    /**
     * Turn for AI players Use this.turn and this.round to calculate which AI
     * player should play this turn
     *
     * turn 0 -> player_h, turn 4 -> player_h turn 1 -> player_1, turn 5 ->
     * player_1 turn 2 -> player_2, turn 6 -> player_2 turn 3 -> player_3, turn
     * 7 -> player_3
     */
    public void player_n_turn() {
        int subtract = (4 * this.round);
        int player_turn = this.turn - subtract;
        int original_board_size = this.board.size();

        switch (player_turn) {
            case 1:
                text.print_turn_start(this.player_1);
                this.player_1.play_turn(this.board, this.stock);
                end_turn(player_1, original_board_size);

                break;
            case 2:
                text.print_turn_start(this.player_2);
                this.player_2.play_turn(this.board, this.stock);
                end_turn(player_2, original_board_size);

                break;
            case 3:
                text.print_turn_start(this.player_3);
                this.player_3.play_turn(this.board, this.stock);
                end_turn(player_3, original_board_size);

                this.round += 1;
                break;
            default:
                break;
        }
    }

}
