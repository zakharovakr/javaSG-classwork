package whiles;

public class LovesMe {

    public static void main(String[] args) {
        int petalCounter = 34;

        System.out.println("I have a daisy...let's see if she loves me...");

        do {
            if (petalCounter % 2 == 0) {
                System.out.println("She loves me!");
                petalCounter--;
            } else {
                System.out.println("She loves me NOT");
                petalCounter--;
            }
        } while (petalCounter >= 0);
        //A do while loop will at least pick one petal
        System.out.println("I knew it! It LOVES ME!");
    }
}