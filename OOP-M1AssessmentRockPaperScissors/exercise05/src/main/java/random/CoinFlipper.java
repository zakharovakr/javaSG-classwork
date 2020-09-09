package random;

import java.util.Random;

public class CoinFlipper {

    public static void main(String[] args) {
        Random randomizer = new Random();

        String coinSide;

        boolean coinBool=randomizer.nextBoolean(); //not using nextInt()

        //2 sides, 2 boolean values

        if (coinBool==true) {
            coinSide="Heads";
        } else {
            coinSide="Tails";
        }

        System.out.println("Ready, set, flip!");
        System.out.println("You got " + coinSide);
    }

}
