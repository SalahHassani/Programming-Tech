package Assignment_1.Simple;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Menu {

    private int xDim, yDim;
    private RandomNumber rand;
    private List<Integer> rolls;
    private List<Player> players;
    private int numPlayers, rounds;
    private List<String> directions;

    public Menu(CyclicDoublyLink parentList, int xDim, int yDim, int numPlayers, List<String> playerNames, int rounds, List<Integer> rolls, int seed, List<String> strategyTypes){

        int balance = 10_000;

        this.xDim = xDim;
        this.yDim = yDim;
        this.rolls = rolls;
        this.rounds = rounds;
        this.numPlayers = numPlayers;
        
        this.directions = new ArrayList<>(Arrays.asList("Up", "Down", "Right", "Left"));
        players = new ArrayList<>();
        rand = new RandomNumber(seed);


        for(int i = 0; i < numPlayers; i++){
            int posX = rand.getRandom(xDim) - 1;
            int posY = rand.getRandom(yDim) - 1;
            players.add(new Player(playerNames.get(i), strategyTypes.get(i), i+1, balance , posX, posY, parentList));
        }
    }

    private void move(int val, String direction, Player player){

        if(player  == null || player.getCurrentNode() == null) return;
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

    public void run(){
        int steps;
        String dir;
        for(int i = 0; i < rounds; i++){

            for(int j = 0; j < this.numPlayers; j++){
                players.get(j).setLastBalance(players.get(j).getBalance()); // setting the last balance for careful players...

                if(players.get(j).getBankcrupcyStatus()) continue; // skip the player

                steps = rolls.get(i*numPlayers + j); // (i)*numPlayers + j => 0.....

                for(int step = 0; step < steps; step++){
                    
                    dir = directions.get(rand.getRandom(4) - 1);
                    move(1, dir, players.get(j));
                    players.get(j).checkAndBalance();

                    if(players.get(j).getBankcrupcyStatus()) break; // skip the player during game
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

    public Player getWinner(){
        if(players.size() == 0) return null;
        Player plr = players.get(0);
        for (Player player : players) {
            if (player.getOwnedProperties().size() > plr.getOwnedProperties().size())
                plr = player;
        }

        return plr;

    }

    
}


