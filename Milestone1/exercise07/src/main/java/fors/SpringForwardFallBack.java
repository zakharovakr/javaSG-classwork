package fors;

public class SpringForwardFallBack {

    public static void main(String[] args) {

        System.out.println("It's Spring...!"); //updated the first loop so that it prints out the same range as the second loop
        for (int i = 1; i < 11; i++) {
            System.out.print(i + ", ");
        }

        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) {
            System.out.print(i + ", ");
        }
    }
}

//start/stop ranges of output for first loop: 0-9
//start/stop ranges of output for second loop: 10-1
