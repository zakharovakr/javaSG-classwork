package fors;

import java.util.Scanner;

public class ForTimesFor {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int score = 0;

        System.out.println("Which times table shall I recite? ");
        int number = Integer.parseInt(myScanner.nextLine());

        for(int i = 1; i <= 15; i++) {
            int answer = number * i;
            System.out.println(i + " * " + number + " is: ");
            int userAnswer = Integer.parseInt(myScanner.nextLine());

            if (userAnswer == answer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Sorry no, the answer is: " + answer);
            }
        }
        System.out.println("You got " + score + " correct.");
    }
}
