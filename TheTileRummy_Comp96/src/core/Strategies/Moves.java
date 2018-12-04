package core.Strategies;

import core.Board;
import core.Player;
import core.Stock;
import core.Text_UI;

public class Moves {

    public Text_UI text = new Text_UI();

    public void first_meld_asap(Player player, Board board, Stock stock) {
        if (player.play_30_plus(board, stock)) {
            text.print_played_first_meld(player);
        } else {
            player.draw(stock, 1);
            text.print_not_enough_points(player);
            text.print_tile_drawn(player, stock);
        }
    }

    public void as_many_ap(Player player, Board board, Stock stock) {
        if (!player.get_hand().has_melds()) {
            player.draw(stock, 1);
            text.print_not_enough_points(player);
            text.print_tile_drawn(player, stock);
        } else {
            while (player.get_hand().has_melds()) {
                player.play_meld(player.get_hand().get_best_meld(), board, stock);
            }
            text.print_played_as_many_ap(player);
        }
    }

    /**
     * p1,p2,p3 {} player 2 will wait until another player play its meld
     *
     * @param player
     * @param board
     * @param stock
     */
    public void first_meld_wait(Player player, Board board, Stock stock) {
        if (!board.is_empty()) {
            first_meld_asap(player, board, stock);
        } else {
            if (player.get_hand().has_30_plus()) {
                text.print_has_melds_must_wait(player);
            } else {
                text.print_not_enough_points(player);
            }
            player.draw(stock, 1);
            text.print_tile_drawn(player, stock);
        }
    }

    public void play_all(Player player, Board board, Stock stock) {
        // Not implemented
    }

    public void play_with_table(Player player, Board board, Stock stock) {
        player.add_tiles_to_board_melds(board);
    }

}
