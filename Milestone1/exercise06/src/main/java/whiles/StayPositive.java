package whiles;

import java.util.Scanner;

public class StayPositive {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //counting down 10-0
        int i = 10;
        while (i >= 0) {
            System.out.println(i);
            i--;
        }

        System.out.println("Blast off!");

        //asking the user for input
        int counter = 0;
        System.out.print("Enter starting value: ");
        int j = scan.nextInt();
        if (j < 0) {
            throw new IllegalArgumentException("Not a positive number");
        }

        while (j >= 0) {
            if (counter%10==0) {
                System.out.print("\n" + j + " ");
                counter++;
                j--;
            } else {
                System.out.print(j + " ");
                counter++;
                j--;
            }

        }
        System.out.println("Blast off!");
    }
}