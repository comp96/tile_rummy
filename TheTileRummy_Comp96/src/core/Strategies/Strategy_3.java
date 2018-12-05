package core.Strategies;

import core.Interfaces.*;
import core.Board;
import core.Player;
import core.Stock;

public class Strategy_3 extends Moves implements Strategy {

    @Override
    public void play_turn(Player player, Board board, Stock stock) {
        if (player.get_melds_played() == 0) {
            first_meld_asap(player, board, stock);//perfoms a meld as soon as a player can

        } else {
            if (player.can_play_all(board)) {  //
                while (player.get_hand().has_melds()) {
                    player.play_meld(player.get_hand().get_best_meld(), board, stock);
                }
            } else {

                if (player.get_max_hand_offset() >= 3) {//its checking of the player has 3 fewer tiles than other players
                    as_many_ap(player, board, stock);
                } else {
                    player.draw(stock, 1);
                }
            }
        }

    }

}
