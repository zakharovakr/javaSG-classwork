package ifs;

import java.util.Scanner;

public class TriviaNight {
    public static void main(String[] args) {
        int score = 0;

        int userGuess1;
        int userGuess2;
        int userGuess3;

        int firstAnswer = 4;
        int secondAnswer = 2;
        int thirdAnswer = 3;

        Scanner myScanner = new Scanner(System.in);

        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        System.out.println("");

        //first question
        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code");
        System.out.println("2) Assembly Language");
        System.out.println("3) C#");
        System.out.println("4) Machine Code");

        userGuess1 = Integer.parseInt(myScanner.nextLine());
        if (userGuess1 == firstAnswer) {
            score++;
        }
        System.out.println("Your answer is " + userGuess1);
        System.out.println("Your current score is " + score);

        //second question
        System.out.println("");
        System.out.println("SECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper");
        System.out.println("2) Alan Turing");
        System.out.println("3) Charles Babbage");
        System.out.println("4) Larry Page");

        userGuess2 = Integer.parseInt(myScanner.nextLine());
        if (userGuess2 == secondAnswer) {
            score++;
        }
        System.out.println("Your answer is " + userGuess2);
        System.out.println("Your current score is " + score);

        //third question
        System.out.println("");
        System.out.println("LAST QUESTION!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity");
        System.out.println("2) The Battlestar Galactica");
        System.out.println("3) The USS Enterprise");
        System.out.println("4) The Millennium Falcon");

        userGuess3 = Integer.parseInt(myScanner.nextLine());
        if (userGuess3 == thirdAnswer) {
            score++;
        }
        System.out.println("Your answer is " + userGuess2);
        System.out.println("Your final score is " + score);

        if (score == 3) {
            System.out.println("Nice job - you got 3 correct!");
        }
        if (score == 2) {
            System.out.println("Nice job - you got 2 ot of 3 correct!");
        }
        if (score == 1) {
            System.out.println("Not bad!");
        }
        if (score == 0) {
            System.out.println("Good luck next time!");
        }

    }
}
