import java.util.Scanner;

public class PassingTheTuringTest {
    public static void main(String[] args) {
        //declare variables for user input
        String userName;
        String userColor;
        String userFood;
        int userNumber;

        //declare variables for the bot
        String name = "Kristina";
        String color = "red";
        String food = "borsch";
        int favoriteNumber = 7;

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Hello there!");
        System.out.print("What is your name? ");
        userName = myScanner.nextLine();

        System.out.println("Hi " + userName + "! I'm " + name);

        System.out.print("What's your favorite color? ");
        userColor = myScanner.nextLine();

        System.out.println("Huh, " + userColor + "? Mine's " + color + ".");

        System.out.println("I really like " + food + ". They're my favorite food, too.");
        System.out.print("What's YOUR favorite food, " + userName + "? ");
        userFood = myScanner.nextLine();

        System.out.println("Really? " + userFood + "? That's wild");
        System.out.print("Speaking of favorites, what's your favorite number? ");
        userNumber = Integer.parseInt(myScanner.nextLine());

        System.out.println(userNumber + " is a cool number. Mine's " + favoriteNumber);

        int total = userNumber * favoriteNumber;
        System.out.println("Did you know that " + userNumber + " * " + favoriteNumber + " is " + total + "? That's a cool number too!");

        System.out.println("Well, thanks for talking to me, " + userName + "!");
    }
}
