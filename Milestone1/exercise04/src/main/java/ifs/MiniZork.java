package ifs;

import java.util.Scanner;

public class MiniZork {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house and east of a barn.");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, go to the barn, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                }
            } else if (action.equals("stick your hand in")) {
                System.out.println("You're dead!'");
            }
        } else if (action.equals("go to the house")) {
            System.out.println("You go the house.");
            System.out.println("It's a gigantic building with a door and running car in the driveway.");
            System.out.print("Go inside or get in the car?");
            action = userInput.nextLine();

            if (action.equals("go inside")) {
                System.out.println("You go inside the house.");
                System.out.println("There are noise everywhere, but you cant see. There is a flashlight in your hand...");
                System.out.print("Scram or turn the flashlight on? ");
                action = userInput.nextLine();

                if (action.equals("turn the flashlight on")) {
                    System.out.println("There are monsters everywhere!!! Bad choice.");
                    System.out.println("You've been eaten by the monsters.");
                } else if (action.equals("Scram")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you're alive. Possibly a wise choice.");
                }
            } else if (action.equals("get in the car")) {
                System.out.println("You hit the gas and get out of there!");
                System.out.println("But you're alive. Possibly a wise choice.");
            }
        }
        else if (action.equals("go to the barn")) {
            System.out.println("You go to the barn.");
            System.out.println("The door is open");
            System.out.print("go inside or run away? ");
            action = userInput.nextLine();

            if (action.equals("go inside")) {
                System.out.println("You go inside the barn.");
                System.out.println("You hear a loud growling");
                System.out.print("Scram or confront the noise? ");
                action = userInput.nextLine();

                if (action.equals("confront the noise")) {
                    System.out.println("There are monsters everywhere!!! Bad choice.");
                    System.out.println("You've been eaten by the monsters.");
                } else if (action.equals("Scram")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you're alive. Possibly a wise choice.");
                }
            }else if (action.equals("run away")) {
                System.out.println("You run away screaming across the fields - looking very foolish.");
                System.out.println("But you alive. Possibly a wise choice.");
            }
        }
    }
}