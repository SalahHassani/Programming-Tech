package Assignment_1.Simple;

import java.util.Random;

public class RandomNumber {
    public Random rand;
    public RandomNumber(int seed){
        rand = new Random(seed);
    }

    public int getRandom(int num){
        return rand.nextInt(num) + 1; // 1 to 6...
    }

}
