package whiles;

import java.util.Random;

public class MaybeItLovesMe {
    public static void main(String[] args) {
        //int petalCounter = 34;
        int randomPetalCounter;
        boolean itLovesMe = true;

        System.out.println("I have a daisy...let's see if she loves me...");

        Random rand = new Random();
        randomPetalCounter = rand.nextInt(77) + 13; // 13-89

        System.out.println(randomPetalCounter);
        do {
            if (itLovesMe) {
                System.out.println("She loves me!");
                randomPetalCounter--;
                itLovesMe= false;
            } else {
                System.out.println("She loves me NOT");
                randomPetalCounter--;
                itLovesMe = true;
            }

        } while (randomPetalCounter > 0);
        //A do while loop will at least pick one petal

        if (itLovesMe) {
            System.out.println("Ahhh...bummer!");
        } else {
            System.out.println("I knew it LOVES ME!!");
        }
    }
}
