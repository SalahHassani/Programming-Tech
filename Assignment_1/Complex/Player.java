package Assignment_1.Complex;


import java.util.ArrayList;
import java.util.List;


public class Player {
    private int posX;
    private int posY;
    private String name;
    private int playerID;
    private double balance;
    private double lastBalance;
    private int PropertyVisits;
    private String strategyType;
    private Boolean isBankcrupt;
    private ChildNode currentNode;


    private List<ChildNode> ownedProperties;

    public Player(String name, String strategyType, int playerID, double balance, int posX, int posY, CyclicDoublyLink parentList){
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.balance = balance;
        this.isBankcrupt = false;
        this.playerID = playerID;
        this.lastBalance = balance;
        this.strategyType = strategyType;
        ownedProperties = new ArrayList<>();
        Node tempNode = (ParentNode) parentList.head;

        for(int y = 0; y < posY; y++){
            tempNode = tempNode.right;
        }

        Node tempchildNode = ((ParentNode) tempNode).childList;

        for(int x = 0; x < posX; x++){
            tempchildNode = tempchildNode.right;
        }

        currentNode = (ChildNode) tempchildNode;
        // toString();

        // checkAndBalance();

    }

    public String getName(){
        return name;
    }

    public int getID(){
        return playerID;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance += balance;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public String getStrategyType(){
        return strategyType;
    }

    public ChildNode getCurrentNode(){
        return this.currentNode;
    }

    public void setCurrentNode(ChildNode node){
        this.currentNode = node;
    }

    public void setPosX(int val, int xDim){
        this.posX = this.posX + val;
        if(this.posX < 0) this.posX = xDim - 1;
        else if(this.posX >= xDim) this.posX = 0;
    }

    public void setPosY(int val, int yDim){
        this.posY = this.posY + val;
        if(this.posY < 0) posY = yDim - 1;
        else if(this.posY >= yDim) this.posY = 0;
    }

    public void addProperty(ChildNode node){
        ownedProperties.add(node);
    }

    @Override
    public String toString(){
        return this.name + " \t\t\t " + strategyType + " \t\t\t    " + this.balance + "    \t\t\t " + ownedProperties.size();
    }


    public void setLastBalance(double bal){
        this.lastBalance = bal;
    }

    public Boolean getBankcrupcyStatus(){
        return isBankcrupt;
    }


    public void checkAndBalance(){
        
        if(currentNode.getOwner() == null){

            // System.out.println("\n=====================" + currentNode.getFieldType());
            if(currentNode.getFieldType().equals(Field.Property)){
                PropertyVisits++;
                // System.out.println("\n=====================" + currentNode.getFieldType() +  " ===> " + PropertyVisits + " ...." + strategyType + " : " + (strategyType.equals("Tactical") ));
                
                if(
                    strategyType.equals("Greedy")
                    || ( strategyType.equals("Carefull") && balance > lastBalance/2) 
                    || (strategyType.equals("Tactical") && PropertyVisits % 2 == 1)
                ){
                    // System.out.println("\n=====================" + currentNode.getFieldType() +  " ===> " + PropertyVisits + " ==> " + strategyType);

                    if(this.balance >= 1000){
                        currentNode.setOwner(this);
                        this.balance -= 1000;
                        ownedProperties.add(currentNode);
                        System.out.println("\n\n\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t:::::::::::::::::::::::::::: "+ this.name +" Has Brought A Property :::::::::::::::::::::::::::");
                        System.out.println("\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
                    }
                    else
                        bankcrupt();
                }
            }
            else if(currentNode.getFieldType().equals(Field.Lucky)){
                this.balance += currentNode.getFieldType().getLuckyBounus();
                System.out.println("\n\n\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("\t\t\t::::::::::::::::::: "+ this.name +" Has Enter A Lucky Field, Got " + currentNode.getFieldType().getLuckyBounus() + " Bonus ::::::::::::::::::::");
                System.out.println("\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
            else{
                if(balance >= currentNode.getFieldType().getServiceFee()){
                    this.balance += currentNode.getFieldType().getServiceFee(); // for service to the bank... no variable for bank just asume the bank get the money....
                    System.out.println("\n\n\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println("\t\t\t:::::::::::::: "+ this.name +" Has Entered A Service Field, Paid " + currentNode.getFieldType().getServiceFee() + " Service Fee ::::::::::::::");
                    System.out.println("\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                }
                else
                    bankcrupt();
            }
            
        }
        else { // visiter is not the owner...
            if(currentNode.getOwner().equals(this)){ // will build a house if the field is empty property
                if(!currentNode.getHouseBuildStatus()) { // if there is no house yet build one...
                    if(this.balance >= 4000){
                        this.balance -= 4000;

                        System.out.println("\n\n\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t::::::::::::::::::::::::::::: "+ this.name +" Is Building A House ::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

                        try{
                            Thread.sleep(1500);
                        }catch(Exception e){ return ;}

                        currentNode.buildHouse();

                    }
                    else
                        bankcrupt();

                }
            }
            else{
                if(currentNode.getHouseBuildStatus()){ // a house is build on field and visiter is not the owner
                    if(balance >= 2000){
                        this.balance -= 2000;
                        currentNode.getOwner().balance += 2000;
                        System.out.println("\n\n\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t::::::::::::::::: "+ this.name +" Has Entered Owned House, Paid 2000 Fine :::::::::::::::::");
                        System.out.println("\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    }
                    else{
                        currentNode.getOwner().balance += this.balance; // give him the remaining balance...
                        bankcrupt();
                    }
                    
                }
                else{
                    if(balance >= 500){
                        this.balance -= 500;
                        currentNode.getOwner().balance += 500;
                        System.out.println("\n\n\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t::::::::::::::::: "+ this.name +" Has Entered Owned Property, Paid 500 Fine :::::::::::::::::");
                        System.out.println("\t\t\t\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    }
                    else
                        bankcrupt();
                }
           
            }
        }
    }

    private void bankcrupt(){
        this.balance = 0;
        this.isBankcrupt = true;
        ownedProperties = new ArrayList<>();

        System.out.println("\n\n\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t\t\t\t\t\t "+ this.name +" Has Decleared Bankcrupcy");
        System.out.println("\t\t\t\t ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");

        for (ChildNode property : ownedProperties) {
            property.reset();
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

