package core;

import core.Interfaces.Strategy;
import core.Strategies.Strategy_1;
import core.Strategies.Strategy_2;
import core.Strategies.Strategy_3;
import org.junit.Test;
import junit.framework.TestCase;

/**
 *
 * @author Comp96
 */
public class SettingsTest extends TestCase {

    Board board = new Board();
    Stock stock = new Stock();
    Text_UI text = new Text_UI();
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

    public SettingsTest() {
    }

    /**
     * Test of getNumOfPlayers method, of class Settings.
     */
    @Test
    public void testGetNumOfPlayers() {
        System.out.println("getNumOfPlayers");
        Settings instance = new Settings();
        int expResult = 5;
        instance.setNumOfPlayers(5);
        int result = instance.getNumOfPlayers();
        assertEquals(expResult, result);

    }

    /**
     * Test of setNumOfPlayers method, of class Settings.
     */
    @Test
    public void testSetNumOfPlayers() {
        System.out.println("setNumOfPlayers");
        int expResult = 5;
        Settings instance = new Settings();
        instance.setNumOfPlayers(5);
        assertEquals(expResult, instance.getNumOfPlayers());

    }

    /**
     * Test of getAI_PlayerCount method, of class Settings.
     */
    @Test
    public void testGetAI_PlayerCount() {
        System.out.println("getAI_PlayerCount");
        Settings instance = new Settings();
        int expResult = 4;
        Strategy_1 st = new Strategy_1();
        Player p1 = new AI_Player(st);
        Player p2 = new AI_Player(st);
        Player p3 = new AI_Player(st);
        Player p4 = new AI_Player(st);
        instance.getPlayers().add(p1);
        instance.getPlayers().add(p2);
        instance.getPlayers().add(p3);
        instance.getPlayers().add(p4);
        int result = instance.getAI_PlayerCount();
        assertEquals(expResult, result);

    }

   
    /**
     * Test of 4 human player exists or not
     */
    @Test
    public void testTwoHumanTwoAIPlayers() {
        System.out.println("testTwoHumanTwoAIPlayers");
        Settings instance = new Settings();
        instance.setNumOfPlayers(4);
        Strategy_1 st = new Strategy_1();
        Player p1 = new AI_Player(st);
        Player p2 = new AI_Player(st);
        Player p3 = new Human_Player("p3");
        Player p4 = new Human_Player("p4");
        instance.getPlayers().add(p1);
        instance.getPlayers().add(p2);
        instance.getPlayers().add(p3);
        instance.getPlayers().add(p4);
        int noOfhumanPlayer = Math.abs(instance.getNumOfPlayers() - instance.getAI_PlayerCount());
        assertEquals(2, instance.getAI_PlayerCount());
        assertEquals(2, noOfhumanPlayer);

    }

    /**
     * Test of Human Can Play Several Groups
     */
    @Test
    public void testHumanCanPlaySeveralGroups() {
        System.out.println("testHumanCanPlaySeveralGroups");
        player_h.add(r_10, stock);
        player_h.add(b_10, stock);
        player_h.add(g_10, stock);
        
        player_h.add(r_11, stock);
        player_h.add(b_11, stock);
        player_h.add(g_11, stock);
        
        player_h.add(r_12, stock);
        player_h.add(b_12, stock);
        player_h.add(g_12, stock);
        
        player_h.add(r_13, stock);
        player_h.add(b_13, stock);
        player_h.add(g_13, stock);

        player_h.get_hand().find_groups();
        
        
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_groups());
        assertEquals(3,player_h.get_hand().get_groups().size());
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_groups());
        assertEquals(2,player_h.get_hand().get_groups().size());
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_groups());
        assertEquals(1,player_h.get_hand().get_groups().size());
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_groups());
        assertEquals(0,player_h.get_hand().get_groups().size());
        
        
    }
    /**
     * Test of Human Can Play Several Runs
     */
    @Test
    public void testHumanCanPlaySeveralRuns() {
        System.out.println("testHumanCanPlaySeveralRuns");
        player_h.add(r_10, stock);
        player_h.add(r_11, stock);
        player_h.add(r_12, stock);
        
        player_h.add(b_11, stock);
        player_h.add(b_12, stock);
        player_h.add(b_13, stock);
        
        player_h.add(g_11, stock);
        player_h.add(g_12, stock);
        player_h.add(g_13, stock);
        
        player_h.add(o_11, stock);
        player_h.add(o_12, stock);
        player_h.add(o_13, stock);

        player_h.get_hand().find_runs();
        
        
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_runs());
        assertEquals(3,player_h.get_hand().get_runs().size());
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_runs());
        assertEquals(2,player_h.get_hand().get_runs().size());
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_runs());
        assertEquals(1,player_h.get_hand().get_runs().size());
        player_h.play_meld_at_index(0, board, stock, player_h.get_hand().get_runs());
        assertEquals(0,player_h.get_hand().get_runs().size());
        
        
    }

}
