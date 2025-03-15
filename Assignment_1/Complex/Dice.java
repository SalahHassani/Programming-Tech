package Assignment_1.Complex;


public class Dice {
    private RandomNumber rand;

    public Dice(){
        rand = new RandomNumber(42);
    }

    public int roll(){
        return rand.getRandom(6);
    }

}
