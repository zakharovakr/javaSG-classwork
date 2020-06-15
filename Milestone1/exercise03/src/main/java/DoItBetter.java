import java.util.Scanner;

public class DoItBetter {
    public static void main(String[] args) {
        int userMiles;
        int userHotDogs;
        int userLanguages;

        Scanner myScanner = new Scanner(System.in);

        System.out.print("How many miles can you run? ");
        userMiles = Integer.parseInt(myScanner.nextLine());
        int computerMiles = userMiles * 2 + 1;
        System.out.println("I can run " + computerMiles + "!!!");

        System.out.print("How many hot dogs can you eat? ");
        userHotDogs = Integer.parseInt(myScanner.nextLine());
        int computerHotDogs = userHotDogs * 2 + 1;
        System.out.println("I can eat " + computerHotDogs + "!!!");

        System.out.print("How many languages can you speak? ");
        userLanguages = Integer.parseInt(myScanner.nextLine());
        int computerLanguages = userLanguages * 2 + 1;
        System.out.println("I can speak " + computerLanguages + "!!!");
    }
}
