package core;

import javax.swing.JOptionPane;

public class Human_Player extends Player {

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
     * @return
     */
    public Meld play_with_group(Board board, Stock stock) {
        Meld meld = null;
        int groups_or_runs_size = getGroupsCount();
        int choice_int = Integer.parseInt(JOptionPane.showInputDialog("\nWhich meld would you like to play (0-" + groups_or_runs_size + ") "));

        while (true) {
            meld = this.play_meld_at_index(choice_int, board, stock, this.get_hand().get_groups());
            if (choice_int <= groups_or_runs_size) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Enter Valid Meld Number (0-" + groups_or_runs_size + ")", "Wrong Input", JOptionPane.WARNING_MESSAGE);
            }

        }

        return meld;
    }

    /**
     * Helper function for player_h_turn
     *
     * @param board
     * @param stock
     * @return
     */
    public Meld play_with_run(Board board, Stock stock) {
        Meld meld = null;
        int groups_or_runs_size = getRunsCount();
        int choice_int = Integer.parseInt(JOptionPane.showInputDialog("\nWhich meld would you like to play (0-" + groups_or_runs_size + ") "));

        if (choice_int <= groups_or_runs_size) {
            meld = this.play_meld_at_index(choice_int, board, stock, this.get_hand().get_runs());
            this.print_hand_and_melds();
        } else {
            System.out.println("invald input");
            play_with_group(board, stock);
        }
        return meld;
    }
}
