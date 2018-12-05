package core;

import core.Interfaces.Strategy;
import core.Strategies.Strategy_1;
import core.Strategies.Strategy_2;
import core.Strategies.Strategy_3;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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

    public Settings() {
        mText_UI = new Text_UI();
        players = new ArrayList<>();
        numOfPlayers = 0;
    }

    public void setupSettings() {
        do {
            System.out.println("Enter Players Count(2-4)");
            setNumOfPlayers(Integer.parseInt(JOptionPane.showInputDialog("Enter Players Count(2-4)")));
        } while (getNumOfPlayers() < 2 || getNumOfPlayers() > 4);
        int choice = 0;
        for (int i = 0; i < getNumOfPlayers(); i++) {
           
            System.out.println("Choose Players Type");
            System.out.println("1- Human");
            System.out.println("2- AI");
            choice= Integer.parseInt(JOptionPane.showInputDialog("Choose Players Type\n1- Human\n2- AI"));
            switch (choice) {
                case 1: {
                    
                    System.out.println("Enter Name of Player ("+(i+1)+"/"+numOfPlayers+")");
                    Player p1 = new Human_Player(JOptionPane.showInputDialog("Enter Name of Player ("+(i+1)+"/"+numOfPlayers+")").toUpperCase());
                    getPlayers().add(p1);
                    break;
                }
                case 2: {
                    Player p1;
                    int strategy_choice= Integer.parseInt(JOptionPane.showInputDialog("Choose Strategy Of layer(1,2 or 3)"));
                    switch (strategy_choice) {
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
            numOfPlayers++;
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
        int AI_PlayerCount=0;
        for(Player p:getPlayers()){
            if(p instanceof AI_Player){
                AI_PlayerCount++;
            }
        }
        return AI_PlayerCount;
    }


}
