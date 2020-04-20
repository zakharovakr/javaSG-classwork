package methods;

import java.util.Random;

public class BarelyControlledChaos {
    public static void main(String[] args) {

        String color = getRandomColor();
        String animal = getRandomAnimal();
        String colorTwo = getRandomColor();

        int weight = getRandomNumber(5, 200);
        int distance = getRandomNumber(5, 200);
        int number = getRandomNumber(10000, 20000);
        int time = getRandomNumber(2, 6);

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
                + weight + "lb " + " miniature " + animal
                + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
                + number + " " + colorTwo + " poppies for nearly "
                + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
                + "let me tell you!");

    }

    public static String getRandomColor(){
        Random rand = new Random();
        int color = rand.nextInt(5);

        switch (color){
            case 0: return "red";
            case 1: return "purple";
            case 2: return "blue";
            case 3: return "green";
            default: return "white";
        }
    }

    public static String getRandomAnimal(){
        Random rand = new Random();
        int animal = rand.nextInt(5);

        switch (animal){
            case 0: return "cat";
            case 1: return "dog";
            case 2: return "parrot";
            case 3: return "llama";
            default: return "frog";
        }
    }

    public static int getRandomNumber(int min, int max){
        Random rand = new Random();
        return (rand.nextInt(max - min) + min + 1);
    }
}
