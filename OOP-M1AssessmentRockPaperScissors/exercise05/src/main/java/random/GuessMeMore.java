package random;

import java.util.Scanner;
import java.util.Random;

public class GuessMeMore {
    public static void main(String[] args) {
        //int answer = 7;
        int randomAnswer;
        int userGuess;

        Random rand = new Random();
        Scanner myScanner = new Scanner(System.in);

        randomAnswer = rand.nextInt(100) +1;

        System.out.println("I've chosen a number between 1 - 100. Betcha can't guess it!");
        //System.out.println(randomAnswer);


        for (int i = 1; i <=3; i++ ) { //giving the user 3 attempts

            System.out.println("Pick a random number:");
            userGuess = Integer.parseInt(myScanner.nextLine());

            System.out.println("Your guess " + userGuess);

            if (randomAnswer == userGuess) {
                System.out.println("Wow, nice guess! That was it!");
                break;
            }

            if (userGuess < randomAnswer) {
                System.out.println("Ha, nice try - too low!");
            }

            if (userGuess > randomAnswer) {
                System.out.println("Too bad, way too high.");
            }
        }
        System.out.println("I chose " + randomAnswer + "!");
    }
}
