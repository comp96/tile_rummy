package core;

public class Main {

    public static void main(String[] args) {
        boolean flag = true;
        int choice = 0;
        while (flag) {
            System.out.println("Welcome to Tile Rummy Game");
            System.out.println("1- Play Game");
            System.out.println("2- Settings");
            System.out.println("3- Exit");
            switch (choice) {
                case 1: {
                    Game game = new Game();
                    game.play_game();//The game starts from here
                    break;
                }
                case 2: {
                    
                }
            }

        }
    }

}
