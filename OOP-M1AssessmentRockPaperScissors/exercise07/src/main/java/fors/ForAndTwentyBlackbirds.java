package fors;

public class ForAndTwentyBlackbirds {
    public static void main(String[] args) {
        int birdsInPie = 0;
        for (int i = 1; i <= 24; i++) { //counting from 1-24
            System.out.println("Blackbird #" + i + " goes into the pie!");
            birdsInPie++;
        }

        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie full!");
    }
}
