package fors;

import java.util.Scanner;

public class TheCount {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int start;
        int stop;
        int count;

        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");

        System.out.print("Start at: ");
        start = Integer.parseInt(myScanner.nextLine());
        System.out.print("Stop at: ");
        stop = Integer.parseInt(myScanner.nextLine());
        System.out.print("Count by: ");
        count = Integer.parseInt(myScanner.nextLine());

        for(int i  = start; i <= stop; i += count){
            System.out.print(i + " ");
        }

    }

}