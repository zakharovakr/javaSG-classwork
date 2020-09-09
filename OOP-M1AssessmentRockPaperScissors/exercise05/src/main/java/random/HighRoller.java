package random;

import java.util.Random;

public class HighRoller {

    public static void main(String[] args) {

        Random diceRoller = new Random();

        int rollResult = diceRoller.nextInt(6) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        } else if (rollResult == 6) {
            System.out.println("You rolled a critical! Nice job!");
        } else {
            System.out.println("That's an average roll.");
        }
    }
}