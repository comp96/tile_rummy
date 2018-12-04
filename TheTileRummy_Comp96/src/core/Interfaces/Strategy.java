package core.Interfaces;

import core.Board;
import core.Player;
import core.Stock;

public interface Strategy {

    /**
     * If player has a meld with a score of 30 or more points, play it
     *
     * @param player
     * @param board
     * @param stock
     */
    void first_meld_asap(Player player, Board board, Stock stock);

    /**
     * Play as many tiles as possible within a turn
     *
     * @param player
     * @param board
     * @param stock
     */
    void as_many_ap(Player player, Board board, Stock stock);

    /**
     * Wait until another player has played at least one meld before player_2
     * plays its first meld
     *
     * @param player
     * @param board
     * @param stock
     */
    void first_meld_wait(Player player, Board board, Stock stock);

    /**
     * If possible, play all tiles in player's hand
     *
     * @param player
     * @param board
     * @param stock
     */
    void play_all(Player player, Board board, Stock stock);

    /**
     * If player can not play all of its tiles this turn, then play only the
     * tiles that require the tiles already on the table
     *
     * @param player
     * @param board
     * @param stock
     */
    void play_with_table(Player player, Board board, Stock stock);

    /**
     * Behavior changes depending on strategy
     *
     * @param player
     * @param board
     * @param stock
     */
    void play_turn(Player player, Board board, Stock stock);

}
