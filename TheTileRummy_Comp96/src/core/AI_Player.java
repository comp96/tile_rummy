package core;

import core.Interfaces.*;

/**
 * @author Heba, Luis
 */
public class AI_Player extends Player {

    /**
     * **********************
     * INSTANCE VARIABLE(S) * **********************
     */
    private final Strategy strategy;

    /**
     * *************
     * CONSTRUCTOR * *************
     */
    /**
     * @param play_strategy strategy of the player
     */
    public AI_Player(Strategy play_strategy) {
        super("player_" + player_id);
        this.strategy = play_strategy;
    }

    /**
     * ***********
     * GETTER(S) * ***********
     */
    /**
     * @return this player's strategy
     */
    public Strategy get_strategy() {
        return this.strategy;
    }

    /**
     * ******
     * ELSE * ******
     */
    /**
     * Play turn depending on strategy
     *
     * @param board
     * @param stock
     */
    public void play_turn(Board board, Stock stock) {
        this.strategy.play_turn(this, board, stock);
    }
}
