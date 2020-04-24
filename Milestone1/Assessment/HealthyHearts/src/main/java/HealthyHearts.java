import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class HealthyHearts {

    //method that takes an age and returns a max heart rate for a person of that age
    public static int getMaxHeartRate(int age) {
        int maxHeartRate = 220 - age;

        return maxHeartRate;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int maxHeartRate;
        int maxTargetHR;
        int minTargetHR;

        //get age from the user
        System.out.println("How old are you?");
        maxHeartRate = getMaxHeartRate(Integer.parseInt(scan.nextLine())); //calling the custom method above with user age as argument

        //calculate the optimal HR zone
        minTargetHR = (int) Math.ceil(maxHeartRate/2); //50% of max heart rate
        maxTargetHR = (int) Math.ceil(maxHeartRate*0.85); //85% of max heart rate

        //display the max and the optimal heart rates
        System.out.println("Your maximum heart rate should be "+ maxHeartRate+ " beats per minute");
        System.out.println("Your target HR Zone is " + minTargetHR +" - " + maxTargetHR + " beats per minute");
    }
}
