package fors;

import java.util.Scanner;

public class ForTimes {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Which times table shall I recite? ");
        int number = Integer.parseInt(myScanner.nextLine());

        for(int i = 1; i <= 15; i++) {
            int answer = number * i;
            System.out.println(i + " * " + number + " is: " + answer);
        }
    }
}
