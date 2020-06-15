package whiles;

import java.util.Random;

public class LazyTeenager {

    public static void main(String[] args) {

        Random rand = new Random();

        int count = 0;
        int chance = 0;
        do {
            count++;
            chance++;

            int cleanRoom = rand.nextInt(11) + chance;
            //System.out.println(cleanRoom);

            System.out.println("Clean your room!! (x" + count+ ")\n");

            if(count >= 10) { //not sure if it's working correctly, might come back to it
                System.out.println("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.\n");
                break;
            }

            if(count == 7) {
                System.out.print("That's IT, I'm doing it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                break;
            }
        } while(true);
    }
}
