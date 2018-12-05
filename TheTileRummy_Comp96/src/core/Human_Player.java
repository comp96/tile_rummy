package core;

import javax.swing.JOptionPane;

public class Human_Player extends Player {

    private static Text_UI text = new Text_UI();

    /**
     * @param name of the player
     */
    public Human_Player(String name) {
        super(name);
    }

    public int getGroupsCount() {
        return this.get_hand().get_groups().size() - 1;
    }
    public int getRunsCount() {
        return this.get_hand().get_runs().size() - 1;
    }

    /**
     * Helper function for player_h_turn
     *
     * @param board
     * @param stock
     */
    public Meld play_with_group(Board board, Stock stock) {
        Meld meld=null;
        int groups_or_runs_size = getGroupsCount();
        int choice_int = Integer.parseInt(JOptionPane.showInputDialog("\nWhich meld would you like to play (0-" + groups_or_runs_size + ") "));

        if (choice_int <= groups_or_runs_size) {
            meld=this.play_meld_at_index(choice_int, board, stock, this.get_hand().get_groups());
            this.print_hand_and_melds();
        } else {
            text.print_invalid_input();
            play_with_group(board, stock);
        }
        return meld;
    }

    /**
     * Helper function for player_h_turn
     *
     * @param board
     * @param stock
     */
    public Meld play_with_run(Board board, Stock stock) {
        Meld meld=null;
        int groups_or_runs_size = getRunsCount();
        int choice_int = Integer.parseInt(JOptionPane.showInputDialog("\nWhich meld would you like to play (0-" + groups_or_runs_size + ") "));

        if (choice_int <= groups_or_runs_size) {
             meld=this.play_meld_at_index(choice_int, board, stock, this.get_hand().get_runs());
            this.print_hand_and_melds();
        } else {
            text.print_invalid_input();
            play_with_group(board, stock);
        }
        return meld;
    }
}
