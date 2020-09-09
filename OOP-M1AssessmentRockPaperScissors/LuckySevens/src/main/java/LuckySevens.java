import java.util.Scanner;

public class LuckySevens {

    //returns a random number between 2-12
    public static int roll() {
        int one = (int) Math.floor(Math.random() * 6 + 1);
        int two = (int) Math.floor(Math.random() * 6 + 1);

        return one + two;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("How many dollars do you have?");

        int bet = Integer.parseInt(scan.nextLine());

        //game loop
        int gameMoney = bet;

        int rollCount = 0;
        int rollTotal;
        int highestAmount = 0;
        int rollCountAtHighestAmount= 0;

    while (gameMoney > 0) {
        //roll
        rollTotal = roll();
        rollCount++;

        //checking if the user got 7 on both dice
        if (rollTotal == 7) {
            gameMoney += 4;
        } else {
            gameMoney --;
        }

        //checking money
        if (gameMoney > highestAmount) {
            highestAmount = gameMoney;
            rollCountAtHighestAmount = rollCount;
        }

    }

        System.out.println("You are broke after " + rollCount + " rolls.");
        System.out.println("You should have quit after " +rollCountAtHighestAmount+  " rolls when you had " + highestAmount + "$.");
    }
}
