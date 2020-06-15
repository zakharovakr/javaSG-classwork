package fors;

import java.util.Scanner;

public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int score = 0;

        System.out.println("How many units of fizzing and buzzing do you need in your life?");
        int playerNumber = Integer.parseInt(myScanner.nextLine());

        int i = 0;
        do {
            i++;
            if (i == 0) {
                System.out.println(i);
            } else if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println("fizz buzz");
                score++;
            } else if (i % 3 == 0) {
                System.out.println("fizz");
                score++;
            } else if (i % 5 == 0) {
                System.out.println("buzz");
                score++;
            } else {
                System.out.println(i);
            }
        } while (playerNumber != score);

            System.out.println("TRADITION!!");
    }
}
