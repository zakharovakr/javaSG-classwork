import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    //global variables
    public static int tieScore = 0;
    public static int userWinScore = 0;
    public static int computerWinScore = 0;

    //this method generates a random number in a range
    // (to later represent computer's choice of Rock, Paper or Scissors)
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    //the game logic method
    public static void playGame() {
        int userChoice;
        int computerChoice;

        Scanner scan = new Scanner(System.in);

        //asking user to choose Rock, Paper or Scissors
        System.out.println("Choose Rock = 1, Paper = 2 or Scissors = 3 (type 1, 2 or 3)");
        userChoice = Integer.parseInt(scan.nextLine());

        //generating computer's choice withing 1-3 range
        computerChoice = getRandomNumberInRange(1, 3);

        //printing out the round results

        //user's choice
        //asking user for a correct entry until they type 1, 2 or 3
        do {
            if (userChoice == 1) {
                System.out.println("You chose Rock");
                break; //if the entry is correct, breaks out of the loop and continues the game
            } else if (userChoice == 2) {
                System.out.println("You chose Paper");
                break;
            } else if (userChoice == 3) {
                System.out.println("You chose Scissors");
                break;
            } else {
                System.out.println("Invalid Entry!!! Enter 1, 2 or 3.");
                System.out.println("Choose Rock = 1, Paper = 2 or Scissors = 3 (type 1, 2 or 3)");
                userChoice = Integer.parseInt(scan.nextLine());
            }
        } while (true);

        //computer's choice
        if (computerChoice == 1) {
            System.out.println("Computer chose Rock");
        } else if (computerChoice ==2) {
            System.out.println("Computer chose Paper");
        } else if (computerChoice ==3) {
            System.out.println("Computer chose Scissors");
        }

        //defining the round's winner
        if (userChoice == computerChoice) {
            tieScore++;
            System.out.println("It's a tie!");
        } else if ((userChoice == 1 && computerChoice == 3)
                || (userChoice == 2 && computerChoice == 1)
                || (userChoice == 3 && computerChoice == 2)) {
            userWinScore++;
            System.out.println("You win!");
        } else {
            computerWinScore++;
            System.out.println("naww.. Computer wins this time!");
        }
    }

    public static void main(String[] args) {
        boolean wannaPlay = true;
        int rounds;
        String keepPlaying;

        Scanner scan = new Scanner(System.in);

        //the game is played at least once, then user is asked if he wants to keep playing
        do {

            //asking user for the amount of rounds
            System.out.println("How many rounds would you like to play? (10 is max)");
            rounds = Integer.parseInt(scan.nextLine());

            //asking again if user enters either a negative amount of rounds
            //or more than 10 rounds
            if (rounds > 10 || rounds < 1) {
                System.out.println("Please, choose between 1 and 10.");
                System.out.println("How many rounds would you like to play? (10 is max)");
                rounds = Integer.parseInt(scan.nextLine());
            }

            //playing as many rounds as user wants
            for (int i =1; i<= rounds; i++) {
                System.out.println("");
                System.out.println("Round " + i + ":");
                playGame();
            }

            //defining game winner
            System.out.println("");
            if (computerWinScore == userWinScore) {
                System.out.print("Your score: " + userWinScore + ", ");
                System.out.print("Computer score: " + computerWinScore + ", ");
                System.out.print("Tie score: " + tieScore + ".");

                System.out.println("\n***Friendship wins!!! (it's a tie).***");

            } else if (userWinScore > computerWinScore) {
                System.out.print("Your score: " + userWinScore + ", ");
                System.out.print("Computer score: " + computerWinScore + ", ");
                System.out.print("Tie score: " + tieScore + ".");

                System.out.println("\n***You won this game!!! Yay!***");

            } else if (computerWinScore > userWinScore) {
                System.out.print("Your score: " + userWinScore + ", ");
                System.out.print("Computer score: " + computerWinScore + ", ");
                System.out.print("Tie score: " + tieScore + ".");

                System.out.println("\n***Sorry, bad luck! Computer wins. You can try again...***");
            }

            //asking user if he wants to continue playing
            //asking till user enters either "y" or "n"
            while (true) {
                System.out.println("Wanna keep playing? y/n");
                keepPlaying = scan.nextLine();
                if (keepPlaying.contains("n")) {
                    System.out.println("It was nice playing with you!");
                    wannaPlay = false;
                    break;//break out of the loop if the entry is valid
                } else if (keepPlaying.contains("y")) {
                    wannaPlay = true;
                    //resetting the game score
                    tieScore = 0;
                    userWinScore = 0;
                    computerWinScore = 0;
                    break;
                } else {
                    System.out.println("I need y or n.");
                }
            }

        } while (wannaPlay);

    }
}
