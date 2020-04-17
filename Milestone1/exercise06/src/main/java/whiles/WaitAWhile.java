package whiles;

public class WaitAWhile {

    public static void main(String[] args) {

        int timeNow = 5;
        int bedTime = 10;

        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up just a liiiiittle longer....");
            timeNow++; // Time passes
        }

        System.out.println("Oh. It's " + timeNow + " o'clock.");
        System.out.println("Guess I should go to bed ...");

        //if you change bedtime value to 11, the loop will go through one more iteration
        //if you change time now to 11, the loop won't run
        //if you comment out timeNow++ the loop will never end
    }
}
