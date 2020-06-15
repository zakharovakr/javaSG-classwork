package ifs;

import java.util.Scanner;

public class YourLifeInMovies {
    public static void main(String[] args) {
        String userName;
        int userBorn;

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Hey, let's play a game! What's your name?");
        userName = myScanner.nextLine();

        System.out.println("Ok, " + userName + " when were you born?");
        userBorn = Integer.parseInt(myScanner.nextLine());

        System.out.println("Well " + userName + "...");

        if (userBorn < 2005) {
            System.out.println("Did you know that Pixar's 'Up' came out over a decade ago?");
        }

        if (userBorn < 1995) {
            System.out.println("And the first Harry Potter came out over 15 years ago!");
        }

        if (userBorn < 1985) {
            System.out.println("Also, Space Jam came out not last decade, but the one before THAT.");
        }

        if (userBorn < 1975) {
            System.out.println("And the original Jurassic Park release is closer to the first lunar landing than it is to today.");
        }

        if (userBorn < 1965) {
            System.out.println("And on top of that, the MASH TV series has been around for almost half a century!");
        }

    }
}
