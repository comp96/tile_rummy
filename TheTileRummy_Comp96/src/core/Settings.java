package core;

import core.Interfaces.Strategy;
import core.Strategies.Strategy_1;
import core.Strategies.Strategy_2;
import core.Strategies.Strategy_3;
import java.util.ArrayList;

/**
 *
 * @author Comp96
 */
public class Settings {

    private static final Strategy STRATEGY_1 = new Strategy_1();
    private static final Strategy STRATEGY_2 = new Strategy_2();
    private static final Strategy STRATEGY_3 = new Strategy_3();
    private int numOfPlayers;
    private final Text_UI mText_UI;
    private ArrayList<Player> players;
    private int AI_PlayerCount;

    public Settings() {
        mText_UI = new Text_UI();
        players = new ArrayList<>();
        AI_PlayerCount = 0;
        numOfPlayers = 0;
    }

    public void setupSettings() {
        do {
            System.out.println("Enter Players Count(2-4)");
            setNumOfPlayers(getmText_UI().get_scanner().nextInt());
        } while (getNumOfPlayers() < 2 || getNumOfPlayers() > 4);
        int choice = 0;
        for (int i = 0; i < getNumOfPlayers(); i++) {
            System.out.println("Choose Players Type");
            System.out.println("1- Human");
            System.out.println("2- AI");
            choice=getmText_UI().get_scanner().nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Enter Name of Player");
                    Player p1 = new Human_Player(getmText_UI().get_scanner().next());
                    getPlayers().add(p1);
                    break;
                }
                case 2: {
                    setAI_PlayerCount(getAI_PlayerCount() + 1);
                    Player p1;
                    switch (getAI_PlayerCount()) {
                        case 1:
                            p1 = new AI_Player(getSTRATEGY_1());
                            break;
                        case 2:
                            p1 = new AI_Player(getSTRATEGY_2());
                            break;
                        default:
                            p1 = new AI_Player(getSTRATEGY_3());
                            break;
                    }
                    getPlayers().add(p1);
                    break;
                }
            }//end of switch
        }
    }

    /**
     * @return the STRATEGY_1
     */
    public static Strategy getSTRATEGY_1() {
        return STRATEGY_1;
    }

    /**
     * @return the STRATEGY_2
     */
    public static Strategy getSTRATEGY_2() {
        return STRATEGY_2;
    }

    /**
     * @return the STRATEGY_3
     */
    public static Strategy getSTRATEGY_3() {
        return STRATEGY_3;
    }

    /**
     * @return the numOfPlayers
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * @param numOfPlayers the numOfPlayers to set
     */
    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    /**
     * @return the mText_UI
     */
    public Text_UI getmText_UI() {
        return mText_UI;
    }

    /**
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @return the AI_PlayerCount
     */
    public int getAI_PlayerCount() {
        return AI_PlayerCount;
    }

    /**
     * @param AI_PlayerCount the AI_PlayerCount to set
     */
    public void setAI_PlayerCount(int AI_PlayerCount) {
        this.AI_PlayerCount = AI_PlayerCount;
    }

}
