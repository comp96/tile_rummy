package core.Strategies;

import core.Interfaces.*;
import core.Board;
import core.Player;
import core.Stock;

public class Strategy_1 extends Moves implements Strategy {

    @Override
    public void play_turn(Player player, Board board, Stock stock) {
        if (player.get_melds_played() == 0) {
            first_meld_asap(player, board, stock);
        } else {
            as_many_ap(player, board, stock);
        }
    }

}
