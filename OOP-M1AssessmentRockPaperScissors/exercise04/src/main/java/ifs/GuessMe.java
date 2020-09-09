package ifs;

import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args) {
        int answer = 7;
        int userGuess;

        Scanner myScanner = new Scanner(System.in);

        System.out.println("I've chosen a number. Betcha can't guess it!");
        System.out.println("Pick a random number:");
        userGuess = Integer.parseInt(myScanner.nextLine());

        System.out.println("Your guess " + userGuess);

        if (answer == userGuess) {
            System.out.println("Wow, nice guess! That was it!");
        }

        if (userGuess < answer) {
            System.out.println("Ha, nice try - too low! I chose " + answer + "!");
        }

        if (userGuess > answer) {
            System.out.println("Too bad, way too high. I chose " + answer + "!");
        }
    }
}
