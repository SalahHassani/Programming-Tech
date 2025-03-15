package Assignment_1.Complex;

import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class CapitalGain {

    public static Field getField(String f){
        switch (f) {
            case "P":
                return Field.Property;
            case "L":
                return Field.Lucky;
            case "S":
                return Field.Service; 
            default:
                return null; // to handle Errors...
        }
    }
    public static void main(String[] args) {

        int serviceFee, luckyBonus;
        int rounds = 1, numPlayers = 1;
        int xDim = 1, yDim = 1, seed = 42;
        List<Integer> rolls = new ArrayList<>();
        List<String> playerNames = new ArrayList<>();
        List<String> allFieldTypes = new ArrayList<>();
        CyclicDoublyLink parentList = new CyclicDoublyLink();
        List<String> strategyTypes = new ArrayList<>();
        // List<String> strategyTypes = new ArrayList<>(Arrays.asList("Greedy", "Careful", "Tactical"));


        String filePath = "Assignment_1/Complex/Data.txt";  // path can be Different for every System...

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // getting the dimentions...
            String[] splitData = line.split(" ");
            xDim = Integer.parseInt(splitData[1]); // X Dimention...
            yDim = Integer.parseInt(splitData[2]); // Y Dimention...

            // get all the FieldTypes ...
            for(int i = 0; i < xDim; i++){
                line = br.readLine();
                splitData = line.split(" ");
                for(int j = 0; j < yDim; j++){
                    allFieldTypes.add(splitData[j]);
                }
            }

            // setting Lucky and Service Fee...
            line = br.readLine();
            splitData = line.split(" ");
            luckyBonus = Integer.parseInt(splitData[1]);
            Field.Lucky.setLuckyBonus(luckyBonus);

            line = br.readLine();
            splitData = line.split(" ");
            serviceFee = Integer.parseInt(splitData[1]);
            Field.Service.setServiceFee(serviceFee);

            // setting No of Players and Their names...
            line = br.readLine();
            splitData = line.split(" ");
            numPlayers = Integer.parseInt(splitData[1]);

            for(int i = 0; i < numPlayers; i++){
                line = br.readLine();
                splitData = line.split(" ");
                playerNames.add(splitData[1]);
                strategyTypes.add(splitData[2]);
            }

            // setting the Rounds
            line = br.readLine();
            splitData = line.split(" ");
            rounds = Integer.parseInt(splitData[1]);

            for(int i = 0; i < rounds; i++){
                line = br.readLine();
                splitData = line.split(" ");
                for(int j = 0; j < numPlayers; j++){
                    rolls.add(Integer.parseInt(splitData[j])); // adding each roll in rolls Integer Array...
                }
            }

            for (int y = 0; y < yDim; y++) {
                parentList.addParentNode();
                CyclicDoublyLink childList = new CyclicDoublyLink();
                for (int x = 0; x < xDim; x++) {
                    childList.addChildNode(y, getField(allFieldTypes.get(y*xDim + x))); // y*5 + x => 0..24
                }

                ((ParentNode) parentList.tail).childList = (ChildNode) childList.head;
            }


            parentList.setUpAndDown();


            Menu menu =  new Menu(parentList, xDim, yDim, numPlayers, playerNames, rounds, rolls, seed, strategyTypes);
            menu.run();
            menu.printAllPlayersData();

        } catch (Exception e) {
            e.printStackTrace();
        }



        // CyclicDoublyLink parentList = new CyclicDoublyLink();
        // for (int y = 0; y < yDim; y++) {
        //     parentList.addParentNode();
        //     CyclicDoublyLink childList = new CyclicDoublyLink();
        //     for (int x = 0; x < xDim; x++) {
        //         childList.addChildNode(y, Field.Property, 400); 
        //     }

        //     ((ParentNode) parentList.tail).childList = (ChildNode) childList.head;
        // }

        // parentList.printListUpAndDown(parentList);
        // parentList.printList();





    
    }
}
