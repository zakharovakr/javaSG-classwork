import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int userAge;
        int maxHeartRate;
        int lowerHeartRate;
        int higherHeartRate;

        System.out.print("What is your age? ");
        userAge = Integer.parseInt(myScanner.nextLine());

        maxHeartRate = 220 - userAge;
        lowerHeartRate = maxHeartRate / 2;
        higherHeartRate = (int) (maxHeartRate * .85);//not perfect calculations here, should be 145 if the age is 50
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
        System.out.println("Your target HR Zone is  " + lowerHeartRate + " - " + higherHeartRate + " beats per minute.");

    }
}
