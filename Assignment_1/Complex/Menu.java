package Assignment_1.Complex;


import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Menu {

    private int xDim, yDim;
    private RandomNumber rand;
    private List<Player> players;
    private int numPlayers, rounds;
    private List<String> directions;
    // private List<String> playerNames;
    // private List<String> strategyTypes;
    private List<Integer> rolls;
    
    private final int multiplier = 100;

    public Menu(CyclicDoublyLink parentList, int xDim, int yDim, int numPlayers, List<String> playerNames, int rounds, List<Integer> rolls, int seed, List<String> strategyTypes){

        int balance = 10_000;

        this.xDim = xDim;
        this.yDim = yDim;
        this.rolls = rolls;
        this.rounds = rounds;
        this.numPlayers = numPlayers;
        // this.playerNames = playerNames;
        // this.strategyTypes = strategyTypes;
        
        this.directions = new ArrayList<>(Arrays.asList("Up", "Down", "Right", "Left"));
        players = new ArrayList<>();
        rand = new RandomNumber(seed);


        for(int i = 0; i < numPlayers; i++){
            int posX = rand.getRandom(xDim) - 1;
            int posY = rand.getRandom(yDim) - 1;
            
            // players.add(new Player("Player_" + (i + 1), this.strategyTypes.get(rand.getRandom(this.strategyTypes.size()-1)), i + 1, 10_000 , posX, posY, parentList));
            players.add(new Player(playerNames.get(i), strategyTypes.get(i), i+1, balance , posX, posY, parentList));

            
        }
    }

    private void welcome() {
        System.out.println("\n\n\n");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '                                                                 ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@    @@   @@@@@   @@      @@@@@@   @@@@@@@   @@    @@   @@@@@@   ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@    @@   @@      @@     @@        @@   @@   @@@  @@@   @@       ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@ @@ @@   @@@@@   @@     @@        @@   @@   @@ @@ @@   @@@@@    ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@@  @@@   @@      @@     @@        @@   @@   @@    @@   @@       ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@    @@   @@@@@   @@@@@   @@@@@@   @@@@@@@   @@    @@   @@@@@@   ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '                                                                 ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\n\n\n");
    }

    private void gameover() {
        System.out.println("\n\n\n");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '                                                                               ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@@@@@@     @@@     @@    @@   @@@@@@     @@@@@@@   @@    @@   @@@@@   @@@@@    ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '  @@         @@   @@   @@@  @@@   @@         @@   @@   @@    @@   @@      @@  @@   ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '  @@  @@@@   @@@@@@@   @@ @@ @@   @@@@@      @@   @@    @@  @@    @@@@@   @@@@@    ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '  @@    @@   @@   @@   @@    @@   @@         @@   @@     @@@@     @@      @@  @@   ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '   @@@@@@@   @@   @@   @@    @@   @@@@@@     @@@@@@@      @@      @@@@@   @@   @@  ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '                                                                               ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\t\t\t\t ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' ' '");
        System.out.println("\n\n\n");
    }

    private void move(int val, String direction, Player player){

        switch(direction){
            case "Up": 
                player.setPosX(-val, xDim);
                player.setCurrentNode((ChildNode) player.getCurrentNode().left);
                break;
            case "Down": 
                player.setPosX(val, xDim);
                player.setCurrentNode((ChildNode) player.getCurrentNode().right);
                break;
            case "Right": 
                player.setPosY(val, yDim);
                player.setCurrentNode(player.getCurrentNode().up);
                break;
            case "Left": 
                player.setPosY(-val, yDim);
                player.setCurrentNode(player.getCurrentNode().down);
        }
    }

    public void play(){
        //Dice dice = new Dice(); // One dice, many players take turns to play
        int steps;
        String dir;
        for(int i = 0; i <= rounds; i++){

            if(i != 0) {
                System.out.println("\n\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("\t\t\t\t :::::::::::::::::::::::::::::::: ROUND_" + i +  " :::::::::::::::::::::::::::::::");
                System.out.println("\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
                sleep(10 );
            }

            

            for(int j = 0; j < this.numPlayers; j++){
                players.get(j).setLastBalance(players.get(j).getBalance()); // setting the last balance for careful players...

                if(i == 0) {
                    System.out.println("\n\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println("\t\t\t\t\t\t\t 1st POSITION OF " + players.get(j).getName());
                    System.out.println("\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
                    printBoard(xDim, yDim, players.get(j).getPosX(), players.get(j).getPosY());
                    System.out.println();
                    sleep(10 );
                }
                else{
                    if(players.get(j).getBankcrupcyStatus()) continue; // skip the player

                    // steps = dice.roll(); // random rolls...
                    steps = rolls.get((i-1)*numPlayers + j); // (i-1)*numPlayers + j => 0..11 since it runs one extra time for positions...

                    System.out.println("\n\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println("\t\t\t\t\t\t\t\t " + players.get(j).getName()+  "'S TURN");
                    System.out.println("\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

                    System.out.print("\t\t\t\t\t\t\t\t  ROLLING");
                    for(int dot = 0; dot < 4; dot++){
                        try {          
                            Thread.sleep(5);        
                            System.out.print(".");
                        } catch (Exception e) {}
                    }

                    System.out.println("\n\n\t\t\t\t ::::::::::::::::::::::::::::::::: => " + steps + " :::::::::::::::::::::::::::::::::\n");
                    sleep(10 );
                    for(int step = 0; step < steps; step++){
                        dir = directions.get(rand.getRandom(4) - 1);
                        System.out.println("\n\n\t\t\t\t :::::::::::::: " + (steps-step) + " MORE STEP TO GO :::::::::::::");
                        System.out.println("\t\t\t\t\t\t One Step => " + dir + " \n");
                        move(1, dir, players.get(j));
                        printBoard(xDim, yDim, players.get(j).getPosX(), players.get(j).getPosY());
                        players.get(j).checkAndBalance();
                        if(players.get(j).getBankcrupcyStatus()) break; // skip the player during game

                        sleep(10);

                    }
                }
            }
        }
    }

    public void printAllPlayersData(){
        System.out.println("Name \t\t\t Strategy Type \t\t\t    Balance  \t\t\t Number of Owned Properties\n");
        for (Player player : players) {
            System.out.println(player.toString()+ "\n");
        }
    }

    private void printBoard(int rows, int cols, int xDim, int yDim){
        
        Board board = new Board();
        board.printBoard(cols, rows, xDim, yDim);
    }

    private void sleep(int sec){
        try {          
            Thread.sleep(sec * multiplier);        
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {}
    }

    public void run(){
        welcome();
        printBoard(xDim, yDim, xDim + 1, yDim + 1);
        sleep(10);
        play();
        sleep(10);
        gameover();
        // for (Player player : players) {
        //     player.toString();
        // }
    }

    
}


