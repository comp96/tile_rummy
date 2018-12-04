package core;

import core.Strategies.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author comp96
 */
public class Game {

    /**
     * CLASS VARIABLE(S)
     */
    private final static int INITIAL_HAND_SIZE = 14;	// Initial hand size will always be the same for all Games
    private final static int WINNING_HAND_SIZE = 0;
    private static Text_UI text = new Text_UI();

    private static int choice_int;
    private static String choice_str;

    /**
     * INSTANCE VARIABLE(S)
     */
    private Board board; // if this.board.get_size() == 0, then no melds have been played
    private Stock stock;
    private boolean end_game;
    private Settings gameSettings;

    public Game() {
        this.board = new Board();
        this.stock = new Stock();
        this.end_game = false;

        gameSettings = new Settings();
        gameSettings.setupSettings();

        for (Player p : gameSettings.getPlayers()) {
            p.register(p);
            p.draw(stock, INITIAL_HAND_SIZE);
            p.print_hand();
        }
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
        if (player.get_hand().size() <= WINNING_HAND_SIZE || this.stock.is_Empty()) {
            this.end_game = true;	// To test we can change this from == 0 to == 10
        }
    }

    /**
     * To Change Settings of the Game(players choice human or AI)
     */
    public void settings() {

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("settings.txt"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Play this game
     */
    public void play_game() {

        text.print_welcome();
        while (!this.end_game) {
            Player lastPlayer = null;
            int count = 0;
            for (Player p : gameSettings.getPlayers()) {
                //if player is a human player
                if (p instanceof Human_Player) {
                    Human_Player player = (Human_Player) p;
                    player_h_turn(player);
                } else {        //if player is a AI player
                    AI_Player player = (AI_Player) p;
                    text.print_turn_start(player);
                    player.play_turn(this.board, this.stock);
                    //if last player is AI and have 3rd strategy 
                    if (player.get_strategy() instanceof Strategy_3) {
                        player.set_max_hand_offset(0);
                    }
                }
                //checking game is end or not
                check_end_game(lastPlayer);
                lastPlayer = p; //saving last player instance for further use
            }
            //notify all observers
            if (lastPlayer != null) {
                text.print_turn_end(lastPlayer, board, this.board.size());
                lastPlayer.notify_observers();

            }
        }
        for (Player p : gameSettings.getPlayers()) {
            if (p.get_hand().size() <= WINNING_HAND_SIZE) {
                text.print(p.get_name() + " has won the game");
            }
        }
    }

    /**
     * Human player's turn
     *
     * @param player
     */
    public void player_h_turn(Human_Player player) {
        int original_board_size = this.board.size();
        text.print_turn_start(player);

        if (player.get_melds_played() > 0 && player.get_hand().has_melds()) {
            player.get_hand().print_melds();
            player.draw_or_play(board, stock);
        } else if (player.get_melds_played() == 0 && player.get_hand().has_30_plus()) {
            player.get_hand().print_melds();
            player.draw_or_play(board, stock);
        } else {
            text.print_not_enough_points(player);
            player.draw(this.stock, 1);
            text.print_tile_drawn(player, stock);
        }

    }

}
