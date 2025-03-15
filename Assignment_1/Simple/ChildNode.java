package Assignment_1.Simple;

public class ChildNode extends Node {
    public int PID;
    public ChildNode up;
    public ChildNode down;

    private Field field;
    private Player owner;
    private boolean houseBuildedOnField;
    public ChildNode(Field field) {
        this.up = null;
        this.down = null;
        this.owner = null;
        this.field = field;
        this.houseBuildedOnField = false;
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

    public void reset(){
        this.owner = null;
        this.houseBuildedOnField = false;
    }
}