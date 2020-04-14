package GuessGame;

import java.util.Scanner;
import java.util.Random;

/* Create a Guessing game
-produce a random number 0<number<100
-let user guess the number
-for each guess....
- respond with
    too low
    too hign
    correct
respond with total number of guesses*/

public class Guess {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //generate a random number
        Random rand = new Random();

        int attempts = 0;

        int numberToGuess = rand.nextInt(100); //generating a random number 0-99
        System.out.println(numberToGuess);

        System.out.println("Guess a random number from 0 to 99.");
        int userGuess = scan.nextInt();

        while (userGuess != numberToGuess) {
            if (userGuess < numberToGuess) {
                System.out.println("too low!");
            } else if (userGuess > numberToGuess) {
                System.out.println("too high!");
            }
            attempts++;
            System.out.println("Try again! Enter another number: ");
            userGuess = scan.nextInt();
        }
        System.out.println("Correct! You guessed the random number after " + attempts + " attempts. Good job!");
    }
}
