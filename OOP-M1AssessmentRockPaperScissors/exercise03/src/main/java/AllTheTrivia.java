import java.util.Scanner;

public class AllTheTrivia {
    public static void main(String[] args) {
        String questionOne;
        String questionTwo;
        String questionThree;
        String questionFour;

        Scanner myScanner = new Scanner(System.in);
        System.out.print("What unit is equivalent to 1,024 Gigabytes? ");
        questionOne = myScanner.nextLine();

        System.out.print("Which planet is the only one that rotates clockwise in our Solar System? ");
        questionTwo = myScanner.nextLine();

        System.out.print("The largest volcano ever discovered in our Solar System is located on which planet? ");
        questionThree = myScanner.nextLine();

        System.out.print("What is the most abundant element in the earth's atmosphere? ");
        questionFour = myScanner.nextLine();

        System.out.println("\nWow, 1,024 Gigabytes is a " + questionFour + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on " + questionOne + "!");
        System.out.println("That's amazing that " + questionTwo + " is the only planet that rotates clockwise, neat!");
        System.out.println(questionOne + " is the only planet that rotates clockwise, neat!");
    }
}
