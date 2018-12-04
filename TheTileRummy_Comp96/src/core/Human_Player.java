package core;

import java.util.ArrayList;

public class Human_Player extends Player {

    private static Text_UI text = new Text_UI();

    /**
     * @param name of the player
     */
    public Human_Player(String name) {
        super(name);
    }

    /**
     *
     * @param board
     * @param stock
     */
    public void play_another_meld(Board board, Stock stock) {
        if (this.get_hand().has_melds()) {
            text.print("Play another meld (y/n)?");
            String choice_str = text.get_scanner().next();

            switch (choice_str) {
                case "y":
                    choose_group_or_run(board, stock);
                    break;
                case "n":
                    break;
                default:
                    text.print_invalid_input();
                    choose_group_or_run(board, stock);
                    break;
            }
        }
    }

    /**
     * Helper function for player_h_turn
     *
     * @param groups_or_runs
     * @param board
     * @param stock
     */
    public void choose_meld(ArrayList<Meld> groups_or_runs, Board board, Stock stock) {
        int groups_or_runs_size = groups_or_runs.size() - 1;
        text.print("\nWhich meld would player_h like to play (0-" + groups_or_runs_size + "): ");
        int choice_int = text.get_scanner().nextInt();

        if (choice_int <= groups_or_runs_size) {
            this.play_meld_at_index(choice_int, board, stock, groups_or_runs);
            this.print_hand_and_melds();
            play_another_meld(board, stock);
        } else {
            text.print_invalid_input();
            choose_meld(groups_or_runs, board, stock);
        }
    }

    /**
     * A second helper function for player_h_turn
     *
     * @param board
     * @param stock
     */
    public void choose_group_or_run(Board board, Stock stock) {
        text.print("\nWould player_h like to play a group or run (g/r): ");
        String choice_str = text.get_scanner().next();

        switch (choice_str) {
            case "g":
                choose_meld(this.get_hand().get_groups(), board, stock);
                break;
            case "r":
                choose_meld(this.get_hand().get_runs(), board, stock);
                break;
            default:
                text.print_invalid_input();
                choose_group_or_run(board, stock);
                break;
        }
    }

    /**
     * A third helper function for player_h_turn
     *
     * @param board
     * @param stock
     */
    public void draw_or_play(Board board, Stock stock) {
        text.print("\nPlease choose 'd' to draw or 'p' to play:");
        String choice_str = text.get_scanner().next();

        switch (choice_str) {
            case "d":
                this.draw(stock, 1);
                break;
            case "p":
                choose_group_or_run(board, stock);
                break;
            default:
                text.print_invalid_input();
                draw_or_play(board, stock);
        }
    }
}
