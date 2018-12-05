package core.Strategies;

import core.Interfaces.*;
import core.Board;
import core.Player;
import core.Stock;

public class Strategy_2 extends Moves implements Strategy {

    @Override
    public void play_turn(Player player, Board board, Stock stock) {
        if (player.get_melds_played() == 0) {
            first_meld_wait(player, board, stock);
        } else {
            if (player.can_play_all(board)) {
                while (player.get_hand().has_melds()) {
                    player.play_meld(player.get_hand().get_best_meld(), board, stock);
                }
            } else {
                player.draw(stock, 1);
            }
        }

    }

}
