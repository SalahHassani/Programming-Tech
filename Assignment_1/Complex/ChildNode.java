package Assignment_1.Complex;


// import java.util.Random;


public class ChildNode extends Node {
    public int PID;
    public ChildNode up;
    public ChildNode down;
    private int bonus;
    private Player owner;
    // private String fieldType;
    private Field field;
    private boolean houseBuildedOnField;
    // private Random rand;
    public ChildNode(Field field) {
        this.up = null;
        this.down = null;
        this.owner = null;
        // rand = new Random();
        this.houseBuildedOnField = false;
        // this.bonus = (rand.nextInt(5) + 1) * 100;
        // String[] allFields = {"Property", "Service", "Lucky"};
        // int num = rand.nextInt(100);
        // this.fieldType = allFields[num % allFields.length];
        this.field = field;
        // if(field.equals(Field.Lucky)) field.setLuckyBonus(num);
        // if(field.equals(Field.Service)) field.setServiceFee(num);


        
    }

    public ChildNode(ChildNode up, ChildNode down, ChildNode left, ChildNode right) {
        super(left, right);

        this.up = up;
        this.down = down;
    }

    public void buildHouse(){
        this.houseBuildedOnField = true;
    }

    public Field getFieldType(){
        return field;
    }

    public boolean getHouseBuildStatus(){
        return houseBuildedOnField;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public Player getOwner(){
        return this.owner;
    }

    public int getBonus(){
        return bonus;
    }

    public void reset(){
        this.owner = null;
        this.houseBuildedOnField = false;
    }
}
