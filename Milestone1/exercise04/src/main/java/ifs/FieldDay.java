package ifs;

import java.util.Scanner;

public class FieldDay {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String userName;

        System.out.println("What is your last name? ");
        userName = scan.nextLine();


        if (userName.compareTo("Baggins") < 0) {
            System.out.println("Aha! You're on the team \"Red Dragons\"!");
        }
        if (userName.compareTo("Baggins") >= 0 && userName.compareTo("Dresden") < 0) {
            System.out.println("Aha! You're on the team \"Dark Wizards\"!");
        }
        if (userName.compareTo("Baggins") >= 0 && userName.compareTo("Dresden") >= 0 && userName.compareTo("Howl") < 0) {
            System.out.println("Aha! You're on the team \"Moving Castles\"!");
        }
        if (userName.compareTo("Baggins") >= 0 && userName.compareTo("Dresden") >= 0 && userName.compareTo("Howl") >= 0
                && userName.compareTo("Potter") < 0) {
            System.out.println("Aha! You're on the team \"Golden Snitches\"!");
        }
        if (userName.compareTo("Baggins") >= 0 && userName.compareTo("Dresden") >= 0 && userName.compareTo("Howl") > 0
                && userName.compareTo("Potter") >= 0 && userName.compareTo("Vimes") < 0) {
            System.out.println("Aha! You're on the team \"Night Guards\"!");
        }
        if (userName.compareTo("Vimes") >= 0) {
            System.out.println("Aha! You're on the team \"Black Holes\"!");
        }
        System.out.println("Good luck in the games!");
    }
}
