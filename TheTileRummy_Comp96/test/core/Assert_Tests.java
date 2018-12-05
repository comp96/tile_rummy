package core;

import core.Interfaces.*;
import core.Strategies.*;
import junit.framework.TestCase;

/**
 * All tests must have assert statements Reading through test console output is
 * helpful but it should not be necessary to know if test is a success Only
 * exceptions are tests for print methods ( e.g. board.print_board() )
 */
public class Assert_Tests extends TestCase {

    Board board = new Board();
    Stock stock = new Stock();
    Hand hand = new Hand();

    Meld meld_0 = new Meld();
    Meld meld_1 = new Meld();
    Meld meld_2 = new Meld();

    Strategy strategy = new Strategy_1();
    Strategy strategy_1 = new Strategy_1();
    Strategy strategy_2 = new Strategy_2();
    Strategy strategy_3 = new Strategy_3();

    Human_Player player_h = new Human_Player("player_h");
    AI_Player player_1 = new AI_Player(strategy_1);
    AI_Player player_2 = new AI_Player(strategy_2);
    AI_Player player_3 = new AI_Player(strategy_3);

    Tile r_1 = new Tile("R", "1");
    Tile r_1_2 = new Tile("R", "1");
    Tile r_2 = new Tile("R", "2");
    Tile r_3 = new Tile("R", "3");
    Tile r_4 = new Tile("R", "4");
    Tile r_5 = new Tile("R", "5");
    Tile r_6 = new Tile("R", "6");
    Tile r_7 = new Tile("R", "7");
    Tile r_8 = new Tile("R", "8");
    Tile r_9 = new Tile("R", "9");
    Tile r_10 = new Tile("R", "10");
    Tile r_11 = new Tile("R", "11");
    Tile r_12 = new Tile("R", "12");
    Tile r_13 = new Tile("R", "13");
    Tile b_1 = new Tile("B", "1");
    Tile b_2 = new Tile("B", "2");
    Tile b_3 = new Tile("B", "3");
    Tile b_4 = new Tile("B", "4");
    Tile b_5 = new Tile("B", "5");
    Tile b_6 = new Tile("B", "6");
    Tile b_7 = new Tile("B", "7");
    Tile b_7_2 = new Tile("B", "7");
    Tile b_8 = new Tile("B", "8");
    Tile b_9 = new Tile("B", "9");
    Tile b_10 = new Tile("B", "10");
    Tile b_10_2 = new Tile("B", "10");
    Tile b_11 = new Tile("B", "11");
    Tile b_12 = new Tile("B", "12");
    Tile b_13 = new Tile("B", "13");
    Tile g_1 = new Tile("G", "1");
    Tile g_2 = new Tile("G", "2");
    Tile g_3 = new Tile("G", "3");
    Tile g_4 = new Tile("G", "4");
    Tile g_4_2 = new Tile("G", "4");
    Tile g_5 = new Tile("G", "5");
    Tile g_5_2 = new Tile("G", "5");
    Tile g_6 = new Tile("G", "6");
    Tile g_7 = new Tile("G", "7");
    Tile g_8 = new Tile("G", "8");
    Tile g_9 = new Tile("G", "9");
    Tile g_10 = new Tile("G", "10");
    Tile g_11 = new Tile("G", "11");
    Tile g_11_2 = new Tile("G", "11");
    Tile g_12 = new Tile("G", "12");
    Tile g_13 = new Tile("G", "13");
    Tile o_1 = new Tile("O", "1");
    Tile o_2 = new Tile("O", "2");
    Tile o_3 = new Tile("O", "3");
    Tile o_4 = new Tile("O", "4");
    Tile o_5 = new Tile("O", "5");
    Tile o_6 = new Tile("O", "6");
    Tile o_7 = new Tile("O", "7");
    Tile o_8 = new Tile("O", "8");
    Tile o_9 = new Tile("O", "9");
    Tile o_10 = new Tile("O", "10");
    Tile o_11 = new Tile("O", "11");
    Tile o_12 = new Tile("O", "12");
    Tile o_13 = new Tile("O", "13");

    /**
     * Test if a player has at least one group in its hand
     */
    public void test_has_groups() {
        player_h.get_hand().add(r_10);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(g_10);
        player_h.get_hand().add(o_10);

        assertTrue(player_h.get_hand().has_groups());
    }

    /**
     * Test if a player has at least one run in its hand
     */
    public void test_has_run() {
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(r_2);
        player_h.get_hand().add(r_3);

        player_h.get_hand_meld().sort_meld();

        assertTrue(player_h.get_hand().has_runs());
    }

    /**
     * Test method for drawing tiles
     */
    public void test_draw() {
        assertEquals(0, player_h.get_hand().size()); // check hand is empty

        Tile temp = stock.peek();

        player_h.draw(stock, 1);
        assertEquals(1, player_h.get_hand().size()); // check there's one card in hand after drawing

        assertEquals(temp, player_h.get_hand().get(0)); // check the card in player's hand is the same that was on top of pile
    }

    /**
     * Test if player with 0 tiles in its hand is acknowledged as a winner
     */
    public void test_win_check() {
        assertTrue(player_h.winner());
        player_h.get_hand().add(b_10);
        assertFalse(player_h.winner());
    }

    /**
     * Test method to sort tiles in hand
     */
    public void test_sort_hand_tiles() {
        Hand h = new Hand();
        h.add(g_10);
        h.add(g_1);
        h.add(r_10);
        h.add(r_3);
        h.add(r_2);
        h.add(r_7);
        h.add(b_10);
        h.add(o_10);
        h.get_hand_meld().sort_meld();

        assertEquals(r_2, h.get(0));
        assertEquals(r_3, h.get(1));
        assertEquals(r_7, h.get(2));
        assertEquals(r_10, h.get(3));
        assertEquals(b_10, h.get(4));
        assertEquals(g_1, h.get(5));
        assertEquals(g_10, h.get(6));
        assertEquals(o_10, h.get(7));
    }

    /**
     * Test method to get the score of a meld
     */
    public void test_meld_score() {
        player_h.get_hand().add(r_10);
        player_h.get_hand().add(r_8);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(g_10);
        player_h.get_hand().add(o_10);

        player_h.get_hand().add(b_1);
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(g_1);
        player_h.get_hand().add(o_1);
        player_h.get_hand().add(b_5);

        player_h.get_hand().find_groups();

        assertEquals(40, player_h.get_hand().get_groups().get(0).get_score());
        assertEquals(4, player_h.get_hand().get_groups().get(1).get_score());
    }

    /**
     * Test method to find groups in a hand
     */
    public void test_find_groups_size_3() {
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(r_2);
        player_h.get_hand().add(r_3);
        player_h.get_hand().add(r_4);
        player_h.get_hand().add(r_5);

        player_h.get_hand().add(b_1);
        player_h.get_hand().add(b_2);
        player_h.get_hand().add(b_3);
        player_h.get_hand().add(b_4);
        player_h.get_hand().add(b_5);

        player_h.get_hand().add(g_1);
        player_h.get_hand().add(g_2);
        player_h.get_hand().add(g_3);
        player_h.get_hand().add(g_4);
        player_h.get_hand().add(g_5);

        player_h.get_hand().add(r_10);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(g_10);

        assertTrue(player_h.get_hand().has_groups());
        assertEquals(6, player_h.get_hand().get_groups().size());
    }

    /**
     * Test method to find groups in a hand
     */
    public void test_find_groups_size_4() {
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(r_2);
        player_h.get_hand().add(r_3);
        player_h.get_hand().add(r_4);
        player_h.get_hand().add(r_5);

        player_h.get_hand().add(b_1);
        player_h.get_hand().add(b_2);
        player_h.get_hand().add(b_3);
        player_h.get_hand().add(b_4);
        player_h.get_hand().add(b_5);

        player_h.get_hand().add(g_1);
        player_h.get_hand().add(g_2);
        player_h.get_hand().add(g_3);
        player_h.get_hand().add(g_4);
        player_h.get_hand().add(g_5);

        player_h.get_hand().add(o_1);
        player_h.get_hand().add(o_2);
        player_h.get_hand().add(o_3);
        player_h.get_hand().add(o_4);
        player_h.get_hand().add(o_5);

        player_h.get_hand().add(r_10);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(g_10);
        player_h.get_hand().add(o_10);

        assertTrue(player_h.get_hand().has_groups());
        assertEquals(6, player_h.get_hand().get_groups().size());
    }

    /**
     * Another test for the method that finds groups in a hand
     */
    public void test_increase_group_size() {
        player_h.get_hand().add(r_10);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(g_10);
        assertEquals(3, player_h.get_hand().size());

        player_h.get_hand().find_groups();
        assertEquals(1, player_h.get_hand().get_groups().size()); // Group of three 10s

        player_h.get_hand().add(o_10);
        assertEquals(4, player_h.get_hand().size());

        player_h.get_hand().find_groups();
        assertEquals(1, player_h.get_hand().get_groups().size()); // Group of four 10s
    }

    /**
     * Fix bug that recognizes groups of 2 if there are duplicate tiles in hand
     * e.g. R6 R6 B6, two R6s, results in group R6 B6
     */
    public void test_find_group_duplicate() {
        //		text.print_test_name("find_group_duplicate");

        player_h.get_hand().add(r_10);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(b_10_2);

        //		player_h.get_hand().print_hand();
        player_h.get_hand().find_groups();
        //		player_h.get_hand().print_groups();

        assertFalse(player_h.get_hand().has_groups());
    }

    /**
     * Test method to find runs in a hand
     */
    public void test_find_runs() {
        // run 1
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(r_2);
        player_h.get_hand().add(r_3);

        // run 2
        player_h.get_hand().add(r_5);
        player_h.get_hand().add(r_6);
        player_h.get_hand().add(r_7);

        player_h.get_hand().add(r_10);

        // run 3
        player_h.get_hand().add(b_8);
        player_h.get_hand().add(b_9);
        player_h.get_hand().add(b_10);

        player_h.get_hand().add(b_13);
        player_h.get_hand().add(g_1);
        player_h.get_hand().add(o_10);

        player_h.get_hand_meld().sort_meld();
        player_h.get_hand().find_runs();
        assertEquals(3, player_h.get_hand().get_runs().size());

        player_h.get_hand_meld().remove(r_1);

        player_h.get_hand_meld().sort_meld();
        player_h.get_hand().find_runs();
        assertEquals(2, player_h.get_hand().get_runs().size());
    }

    /**
     * Test method to play a meld
     */
    public void test_play_group() {
        //		text.print_test_name("play_group");

        player_h.add(r_10, stock);
        player_h.add(b_10, stock);
        player_h.add(g_10, stock);

        player_h.get_hand().find_groups();
        //		player_h.get_hand().print_hand();
        //		player_h.get_hand().print_groups();
        //		board.print_board();

        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_groups());

        //		player_h.get_hand().print_hand();
        //		board.print_board();
        assertTrue(player_h.get_hand().get_groups().isEmpty());
    }

    /**
     * Test method to play a meld
     */
    public void test_play_best_meld() {
        //		text.print_test_name("play_best_meld");

        player_h.draw(stock, 14);

        if (player_h.get_hand().has_melds()) {
            //			player_h.get_hand().print_melds();

            //			text.print("Hand and board before playing meld");
            //			player_h.get_hand().print_hand(); 
            //			board.print_board();
            player_h.play_meld(player_h.get_hand().get_best_meld(), board, stock);

            //			text.print("Hand and board after playing meld");
            //			player_h.get_hand().print_hand();
            //			board.print_board();
            assertEquals(1, board.size());
        } else {
            //			System.out.println("No melds in hand");
            assertEquals(0, board.size());
        }
    }

    /**
     * Test method to add tiles to hand
     */
    public void test_add_to_hand() {
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(r_3);
        player_h.get_hand().add(r_2);
        player_h.get_hand().add(r_4);
        player_h.get_hand().add(r_5);

        assertEquals(5, player_h.get_hand().size());
    }

    /**
     * Test method to print meld to console
     */
    public void test_print_meld() {
        //		text.print_test_name("print_meld");

        meld_0.add(b_10);
        meld_0.add(g_10);
        meld_0.add(o_10);
        //		meld_0.print_meld();

        assertEquals(3, meld_0.size());
    }

    /**
     * Test method to print all melds on board to console
     */
    public void test_print_board() {
        //		text.print_test_name("print_board");

        meld_0.add(b_10);
        meld_0.add(g_10);
        meld_0.add(o_10);
        assertEquals(3, meld_0.size());

        meld_1.add(r_1);
        meld_1.add(r_2);
        meld_1.add(r_3);
        assertEquals(3, meld_1.size());

        board.add(meld_0);
        board.add(meld_1);
        //		board.print_board();
        assertEquals(2, board.size());
    }

    /**
     * Test finding best meld in hand
     */
    public void test_best_meld() {
        player_h.get_hand().add(b_1);
        player_h.get_hand().add(r_1);
        player_h.get_hand().add(g_1);
        player_h.get_hand().add(o_1);

        player_h.get_hand().add(r_10);
        player_h.get_hand().add(b_10);
        player_h.get_hand().add(g_10);
        player_h.get_hand().add(o_10);

        player_h.get_hand().add(b_1);
        player_h.get_hand().add(b_2);
        player_h.get_hand().add(b_3);
        player_h.get_hand().add(b_4);

        player_h.get_hand().add(r_7);
        player_h.get_hand().add(r_8);
        player_h.get_hand().add(r_9);
        player_h.get_hand().add(r_10);
        player_h.get_hand().add(r_11);
        player_h.get_hand().add(r_12);
        player_h.get_hand().add(r_13);

        player_h.get_hand().find_melds();
        assertTrue(player_h.get_hand().get_runs().get(0) == player_h.get_hand().get_best_meld());
    }

    /**
     * Test if player's name is correct Doesn't work for AI players because
     * player_id increases each time a player is created in a test So player_1
     * could have one player_id in one test and another id in a different test
     */
    public void test_player_name() {
        assertTrue(player_h.get_name().equals("player_h"));
    }

    /**
     * Test the strategy to play first meld as soon as possible
     */
    public void test_play_fm_asap() {
        //		text.print_test_name("play_fm_asap");

        player_1.add(r_10, stock);
        //		player_1.get_hand().print_hand();
        player_1.play_turn(board, stock);
        assertEquals(2, player_1.get_hand().size());
        //		player_1.get_hand().print_hand();

        player_1.add(b_10, stock);
        player_1.add(g_10, stock);
        //		player_1.get_hand().print_hand();
        //		player_1.get_hand().print_melds();
        //		board.print_board();

        player_1.get_strategy().first_meld_asap(player_1, board, stock);
        assertEquals(1, board.size());

        //		player_1.get_hand().print_hand();
        //		board.print_board();
    }

    /**
     * Test first meld wait
     */
    public void test_first_meld_wait() {
        //		text.print_test_name("first_meld_wait");

        player_1.add(r_10, stock);
        player_1.add(b_10, stock);
        player_1.add(g_10, stock);
        player_1.add(o_10, stock);

        player_1.add(b_1, stock);
        player_1.add(b_2, stock);

        player_2.add(r_7, stock);
        player_2.add(r_8, stock);
        player_2.add(r_9, stock);
        player_2.add(r_10, stock);
        player_2.add(r_11, stock);

        player_2.play_turn(board, stock);
        player_1.play_turn(board, stock);
        //		board.print_board();

        //		player_2.get_hand().print_hand();
        assertEquals(1, board.size());
        assertTrue(!player_1.get_hand().has_melds());
        assertTrue(player_2.get_hand().has_melds());

        player_2.play_turn(board, stock);
        //		board.print_board();

        assertTrue(board.size() > 1);
        assertTrue(!player_2.get_hand().has_melds());
    }

    /**
     * Test as many ap
     */
    public void test_as_many_ap() {
        //		text.print_test_name("as_many_ap");

        player_1.add(r_10, stock);
        player_1.add(b_10, stock);
        player_1.add(g_10, stock);
        player_1.add(o_10, stock);

        player_1.add(b_1, stock);
        player_1.add(b_2, stock);
        player_1.add(b_3, stock);
        player_1.add(b_4, stock);

        player_1.add(r_1, stock);
        player_1.add(r_2, stock);
        player_1.add(r_3, stock);
        player_1.add(r_4, stock);

        player_1.get_strategy().as_many_ap(player_1, board, stock);

        assertEquals(3, board.size());
        assertTrue(!player_1.get_hand().has_melds());
    }

    /**
     * Test strategy 1
     */
    public void test_strategy_1() {
        //		text.print_test_name("strategy_1");

        player_1.add(r_10, stock);
        player_1.add(b_10, stock);
        player_1.add(g_10, stock);
        player_1.add(o_10, stock);

        player_1.add(b_1, stock);
        player_1.add(b_2, stock);
        player_1.add(b_3, stock);

        player_1.add(r_1, stock);
        player_1.add(r_2, stock);
        player_1.add(r_3, stock);

        player_1.get_hand_meld().sort_meld();

        player_1.get_strategy().play_turn(player_1, board, stock);
        assertEquals(3, board.size());
    }

    /**
     * Play multiple melds that add up to 30+ points for first meld played
     */
    public void test_several_first_melds() {
        //		text.print_test_name("several_first_melds");

        player_1.add(r_2, stock);
        player_1.add(r_3, stock);
        player_1.add(r_4, stock);
        player_1.add(r_5, stock);
        player_1.add(r_6, stock);

        player_1.add(b_1, stock);
        player_1.add(b_2, stock);
        player_1.add(b_3, stock);
        player_1.add(b_4, stock);

        player_1.get_hand_meld().sort_meld();

        player_1.get_strategy().first_meld_asap(player_1, board, stock);
        //		board.print_board();
        assertEquals(2, board.size());
    }

    /**
     *
     */
    public void test_play_30_plus_1() {
        //		text.print_test_name("play_30_plus_1");

        player_1.add(r_10, stock);
        player_1.add(b_10, stock);
        player_1.add(g_10, stock);

        //		player_1.get_hand().print_hand();
        //		board.print_board();
        player_1.play_30_plus(board, stock);

        //		player_1.get_hand().print_hand();
        //		board.print_board();
        assertEquals(1, board.size());
    }

    /**
     * Special case
     */
    public void test_play_30_plus_2() {
        //		text.print_test_name("play_30_plus_2");

        player_1.add(r_6, stock);
        player_1.add(r_7, stock);
        player_1.add(r_8, stock);
        player_1.add(r_9, stock);

        player_1.add(r_5, stock);
        player_1.add(b_5, stock);
        player_1.add(g_5, stock);

        player_1.get_hand_meld().sort_meld();
        //		player_1.get_hand().print_hand();

        player_1.get_hand().find_melds();
        //		player_1.get_hand().print_melds();

        player_1.play_30_plus(board, stock);

        //		player_1.get_hand().print_hand();
        //		board.print_board();
        assertEquals(1, board.size());
    }

    /**
     * Test removing tile shared by a group and a run
     */
    public void test_remove_shared_tile() {
        //		text.print_test_name("remove_shared_tile");

        player_1.add(b_1, stock);
        player_1.add(o_1, stock);
        player_1.add(r_1, stock);

        player_1.add(o_3, stock);
        player_1.add(o_2, stock);

        player_1.get_hand_meld().sort_meld();
        //		player_1.get_hand().print_hand();
        player_1.get_hand().find_groups();
        player_1.get_hand().find_runs();

        //		int i = player_1.get_hand().get_groups().size();
        //		text.print("\nplayer_1.get_hand().get_groups().size() is " + i);
        //		System.out.print("player_1.get_hand().get_groups().get(0) is ");
        //		player_1.get_hand().get_groups().get(i-1).print_meld();
        //		text.print("\ngroup[0][1] hashcode is " + player_1.get_hand().get_groups().get(0).get_tile(1).hashCode());
        //		text.print("runs[0][2] hashcode is " + player_1.get_hand().get_runs().get(0).get_tile(2).hashCode());
        //		text.print("\nBefore");
        //		player_1.get_hand().print_melds();
        assertEquals(1, player_1.get_hand().get_groups().size());

        player_1.get_hand().remove_meld_shared_tile();

        //		text.print("\nAfter");
        //		player_1.get_hand().print_melds();
        assertEquals(0, player_1.get_hand().get_groups().size());
    }

    /**
     * Test removing tile shared by a group and a run
     */
    public void test_remove_shared_tile_2() {
        //		text.print_test_name("remove_shared_tile_2");

        // R1 R3 R4 R5 R7 R11 R12 R13 R13 B4 B6 B8 B10 G4 G4 G6 G8 G9 G12 O1 O2 O5 O7 O13
        // This hand combination originally resulted in an index out of bounds exception
        // Included a continue statement in remove_meld_shared_tile along with a loop label and problem solved
        player_1.add(r_1, stock);
        player_1.add(r_3, stock);
        player_1.add(r_4, stock);
        player_1.add(r_5, stock);
        player_1.add(r_7, stock);
        player_1.add(r_11, stock);
        player_1.add(r_12, stock);
        player_1.add(r_13, stock);
        player_1.add(r_13, stock);
        player_1.add(b_4, stock);
        player_1.add(b_6, stock);
        player_1.add(b_8, stock);
        player_1.add(b_10, stock);
        player_1.add(g_4, stock);
        player_1.add(g_4_2, stock);
        player_1.add(g_6, stock);
        player_1.add(g_8, stock);
        player_1.add(g_9, stock);
        player_1.add(g_12, stock);
        player_1.add(o_1, stock);
        player_1.add(o_2, stock);
        player_1.add(o_5, stock);
        player_1.add(o_7, stock);
        player_1.add(o_13, stock);

        player_1.get_hand_meld().sort_meld();
        //		player_1.get_hand().print_hand();
        player_1.get_hand().find_groups();
        player_1.get_hand().find_runs();

        //		text.print("\nBefore");
        //		player_1.get_hand().print_melds();
        assertEquals(1, player_1.get_hand().get_groups().size());

        player_1.get_hand().remove_meld_shared_tile();

        //		text.print("\nAfter");
        //		player_1.get_hand().print_melds();
        assertEquals(0, player_1.get_hand().get_groups().size());
    }

    /**
     * Test removing tile shared by a group and a run
     */
    public void test_remove_shared_tile_3() {
        //		text.print_test_name("remove_shared_tile_3");

        // R4 R5 R9 B6 B7 B7 B11 B11 G4 G5 G5 G6 G8 G12 G13 O2 O3 O4 O10 O11
        player_1.add(r_4, stock);
        player_1.add(r_5, stock);
        player_1.add(r_9, stock);
        player_1.add(b_6, stock);
        player_1.add(b_7, stock);
        player_1.add(b_7_2, stock);
        player_1.add(b_11, stock);
        player_1.add(g_11_2, stock);
        player_1.add(g_4, stock);
        player_1.add(g_5, stock);
        player_1.add(g_5_2, stock);
        player_1.add(g_6, stock);
        player_1.add(g_8, stock);
        player_1.add(g_12, stock);
        player_1.add(g_13, stock);
        player_1.add(o_2, stock);
        player_1.add(o_3, stock);
        player_1.add(o_4, stock);
        player_1.add(o_10, stock);
        player_1.add(o_11, stock);

        player_1.get_hand_meld().sort_meld();
        //		player_1.get_hand().print_hand();
        player_1.get_hand().find_groups();
        player_1.get_hand().find_runs();

        //		text.print("\nBefore");
        //		player_1.get_hand().print_melds();
        assertEquals(2, player_1.get_hand().get_groups().size());
        assertEquals(2, player_1.get_hand().get_runs().size());

        player_1.get_hand().remove_meld_shared_tile();

        //		text.print("\nAfter");
        //		player_1.get_hand().print_melds();
        assertEquals(1, player_1.get_hand().get_groups().size());
        assertEquals(1, player_1.get_hand().get_runs().size());
    }

    /**
     * Test if a meld is recognized as a run
     */
    public void test_is_run() {
        meld_0.add(b_1);
        meld_0.add(b_2);
        meld_0.add(b_3);

        meld_1.add(r_10);
        meld_1.add(b_10);
        meld_1.add(g_10);
        meld_1.add(o_10);

        assertTrue(meld_0.is_run());
        assertFalse(meld_1.is_run());
    }

    /**
     * Test if player can play all its tiles reusing tiles from board
     */
    public void test_can_play_all() {
        //		text.print_test_name("test_can_play_all");
//r7,g1
//r5,r6,r1,b1
        player_3.add(r_7, stock);
        player_3.add(g_1, stock);

        meld_0.add(r_5);
        meld_0.add(r_6);

        meld_1.add(r_1);
        meld_1.add(b_1);

        board.get_board().add(meld_0);
        board.get_board().add(meld_1);
        //		board.print_board();

        assertTrue(player_2.can_play_all(board));
    }

    /**
     * Ambiguous
     */
    public void test_can_be_added_to_run() {
        //		text.print_test_name("test_can_be_added_to_run()");

        player_2.add(r_4, stock);
        player_2.add(r_6, stock);

        meld_0.add(r_5);
        meld_0.add(r_6);

        board.get_board().add(meld_0);

        assertTrue(player_2.addable_run_tile(meld_0, r_7));
    }

    /**
     * Ambiguous
     */
    public void test_can_be_added_to_group() {
        //		text.print_test_name("test_can_be_added_to_group()");

        player_2.add(g_1, stock);
        //		player_2.get_hand().print_hand();

        meld_0.add(r_1);
        meld_0.add(b_1);

        board.get_board().add(meld_0);
        //		board.print_board();

        assertTrue(player_2.addable_group_tile(meld_0, g_1));
    }

    /**
     * Return true if tile could be added to group on board
     */
    public void test_addable_group_tile() {
        //		text.print_test_name("addable_group_tile");

        meld_0.add(r_3);
        meld_0.add(b_3);
        meld_0.add(o_3);

        assertTrue(player_h.addable_group_tile(meld_0, g_3));
    }

    /**
     * Return true if tile could be added to run on board
     */
    public void test_addable_run_tile() {
        //text.print_test_name("test_addable_run_tile");

        meld_0.add(r_3);
        meld_0.add(r_4);
        meld_0.add(r_5);

        assertTrue(player_h.addable_run_tile(meld_0, r_6));
    }

    /**
     * All tiles in hand that can be played to melds board will be played
     */
    public void test_play_all_1() {

        player_3.add(g_12, stock);
        player_3.add(o_1, stock);
        player_3.add(g_8, stock);

        player_3.print_hand();

        meld_0.add(g_5);
        meld_0.add(g_7);
        meld_0.add(g_6);

        meld_2.add(g_9);
        meld_2.add(g_10);
        meld_2.add(g_11);

        meld_1.add(r_1);
        meld_1.add(b_1);
        meld_1.add(g_1);

        board.add(meld_2);
        board.add(meld_1);
        board.add(meld_0);

        board.print_board();

        player_3.play_tiles_to_table_melds(board);

        player_3.print_hand();
        board.print_board();

        assertTrue(player_2.can_play_all(board));
    }

    /**
     * Test sorting a meld
     */
    public void test_sort_meld() {

        meld_0.add(b_1);
        meld_0.add(r_6);
        meld_0.add(g_10);
        meld_0.add(r_8);
        meld_0.add(r_7);
        meld_0.add(o_11);
        meld_0.add(r_9);

        meld_0.sort_meld();
        assertTrue(meld_0.get_tile(1).ascending_rank_same_color(meld_0.get_tile(0)));
    }
}
