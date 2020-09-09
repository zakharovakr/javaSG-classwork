package ifs;

import java.util.Scanner;

public class KnockKnock {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        //== Compares memory location, equals compares content
        System.out.print("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();

        if(nameGuess.equals("Marty McFly")){ //use can use equalsIgnoreCase() to make it case-insensitive
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future."); // Sorry, had to!
        }else{
            System.out.println("Dude, do I -look- like " + nameGuess);
        }
    }
}
