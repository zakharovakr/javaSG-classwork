import java.util.Random;
import java.util.Scanner;


public class DogGenetics {

    //this takes an array and randomly distributes 100% between the elements of the array
    public static void getDogGenetics(String[] breed) {
        Random rand = new Random();

        int percentSum = 0;
        int percent;

        for (int i = 0; i < breed.length; i++) {
            if (i == breed.length - 1) {
                percent = 100 - percentSum;
            } else {
                percent = rand.nextInt(100 - percentSum + 1);
                percentSum += percent;
            }

            System.out.println(percent + "% " + breed[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String dogName;

        System.out.println("What is your dog's name?");
        dogName = scan.nextLine();

        System.out.println("Well then, I have this highly reliable report on "
                    + dogName + "'s prestigious background right here.");
        System.out.println("");
        System.out.println(dogName + " is:");
        System.out.println("");

        //calling the custom method and passing our array with dog breeds to it
        getDogGenetics(new String[] {"St.Bernard", "Chihuahua", "Dramatic RedNosed Asian Pug", "Common Cur", "King Doberman"});
        System.out.println("");
        System.out.println("Wow, that's QUITE the dog!");
    }
}
