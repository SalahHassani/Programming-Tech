package Assignment_1.Simple;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private int posX;
    private int posY;
    private String name;
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
        this.lastBalance = balance;
        this.strategyType = strategyType;
        ownedProperties = new ArrayList<>();
        Node tempNode = (ParentNode) parentList.head;

        for(int y = 0; y < posY; y++){  tempNode = tempNode.right;  }

        Node tempchildNode = ((ParentNode) tempNode).childList;

        if(tempchildNode == null) return;
        for(int x = 0; x < posX; x++){  tempchildNode = tempchildNode.right;    }

        currentNode = (ChildNode) tempchildNode;

        // if(currentNode != null)
        //     switch (currentNode.getFieldType()) {
        //         case Property:
        //             this.balance -= 1000;
        //             ownedProperties.add(currentNode);
        //             currentNode.setOwner(this);
        //             break;
        //         case Lucky:
        //             this.balance += 500;
        //             ownedProperties.add(currentNode);
        //             currentNode.setOwner(this);
        //             break;
        //         case Service:
        //             this.balance -= 300;
        //             ownedProperties.add(currentNode);
        //             currentNode.setOwner(this);
        //             break;
        //     }

        // checkAndBalance();
    }

    public int getPosX(){ return posX; }
    public int getPosY(){ return posY; }
    public String getName(){ return name; }
    public double getBalance(){ return balance; }

    public String getStrategyType(){ return strategyType; }
    public Boolean getBankcrupcyStatus(){ return isBankcrupt; }
    public ChildNode getCurrentNode(){ return this.currentNode; }
    public List<ChildNode> getOwnedProperties(){ return this.ownedProperties; }

    public void setLastBalance(double bal){ this.lastBalance = bal; }
    public void setBalance(double balance){ this.balance += balance; }
    public void addProperty(ChildNode node){ ownedProperties.add(node);}
    public void setCurrentNode(ChildNode node){ 
        this.currentNode = node;
        return;
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

    private void bankcrupt(){
        this.balance = 0;
        this.isBankcrupt = true;
        ownedProperties = new ArrayList<>();
        for (ChildNode property : ownedProperties) { property.reset(); }
    }

    @Override
    public String toString(){
        return this.name + " \t\t\t " + strategyType + " \t\t\t    " + this.balance + "    \t\t\t " + ownedProperties.size();
    }

    public void checkAndBalance(){
        
        if(currentNode != null && currentNode.getOwner() == null){

            if(currentNode.getFieldType().equals(Field.Property)){
                PropertyVisits++;
                if(
                    strategyType.equals("Greedy")
                    || ( strategyType.equals("Carefull") && balance > lastBalance/2) 
                    || (strategyType.equals("Tactical") && PropertyVisits % 2 == 1)
                ){
                    if(this.balance >= 1000){
                        currentNode.setOwner(this);
                        this.balance -= 1000;
                        ownedProperties.add(currentNode);
                    }
                    else
                        bankcrupt();
                }
            }
            else if(currentNode != null && currentNode.getFieldType().equals(Field.Lucky)){
                this.balance += currentNode.getFieldType().getLuckyBounus();
                return;
            }
            else{
                if(balance >= currentNode.getFieldType().getServiceFee()){
                    this.balance += currentNode.getFieldType().getServiceFee(); // for service to the bank... no variable for bank just asume the bank get the money....
                }
                else
                    bankcrupt();
            }           
        }
        else { // visiter is not the owner...
            if(currentNode != null && currentNode.getOwner().equals(this)){ // will build a house if the field is empty property
                if(!currentNode.getHouseBuildStatus()) { // if there is no house yet build one...
                    if(this.balance >= 4000){
                        this.balance -= 4000;
                        currentNode.buildHouse();
                    }
                    else
                        bankcrupt();
                }
            }
            else{
                if(currentNode != null && currentNode.getHouseBuildStatus()){ // a house is build on field and visiter is not the owner
                    if(balance >= 2000){
                        this.balance -= 2000;
                        currentNode.getOwner().balance += 2000;
                    }
                    else{
                        currentNode.getOwner().balance += this.balance; // give him the remaining balance...
                        bankcrupt();
                    } 
                }
                else{
                    if(balance >= 500){
                        this.balance -= 500;
                        if(currentNode != null )
                            currentNode.getOwner().balance += 500;
                    }
                    else
                        bankcrupt();
                }
            }
        }
    }
}
