package core;

import org.junit.Test;
import junit.framework.TestCase;

/**
 *
 * @author Comp96
 */
public class SettingsTest extends TestCase {

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
        int expResult = 5;
        instance.setAI_PlayerCount(5);
        int result = instance.getAI_PlayerCount();
        assertEquals(expResult, result);

    }

    /**
     * Test of setAI_PlayerCount method, of class Settings.
     */
    @Test
    public void testSetAI_PlayerCount() {
        System.out.println("setAI_PlayerCount");
        int AI_PlayerCount = 5;
        Settings instance = new Settings();
        instance.setAI_PlayerCount(AI_PlayerCount);
        int result = instance.getAI_PlayerCount();
        assertEquals(5, result);
    }

}
