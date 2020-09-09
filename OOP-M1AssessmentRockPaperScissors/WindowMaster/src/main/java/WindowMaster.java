import java.util.Scanner;

public class WindowMaster {
    public static void main(String[] args) {
        //declare variables for window height and width and number of windows

        float height = 0; //I have to initialize the values, otherwise the compiler throws an error. Not sure if there's a better was to do it.
        float width = 0;
        int windowNumber = 0;

        //declare variables for glass cost and trim cost
        float glassCost = 0;
        float trimCost = 0;

        //declare String variables to hold the user's height and width input + number of windows
        String stringHeight = "";
        String stringWidth = "";
        String stringWindowNumber = "";

        //declare String variables to hold the user's height and width input
        String stringGlassCost = "";
        String stringTrimCost = "";

        //declare other variables
        float areaOfWindow = 0;
        float perimeterOfWindow = 0;
        float totalCost = 0;


        // declare and initialize the Scanner
        Scanner myScanner = new Scanner(System.in);

        // get input from the user and make sure it's correct for each case

        boolean isValid = false; //declare a boolean value to check the try/catch examples

        //height input
        do {
            try {
                System.out.println("Please enter the height of the window:");
                stringHeight = myScanner.nextLine();
                height = Float.parseFloat(stringHeight);

                if (height >= 1) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("The height of the window has to be a number. Try again!");
            }
        } while (!isValid);

        isValid = false; //do I have to set it to false each time? After each try/catch instance?

        //width input
        do {
            try {
                System.out.println("Please enter the width of the window:");
                stringWidth = myScanner.nextLine();
                width = Float.parseFloat(stringWidth);

                if (width >= 1) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("The width of the window has to be a number. Try again!");
            }
        } while (!isValid);

        isValid = false;

        //glass cost input
        do {
            try {
                System.out.println("Please enter the glass cost per square foot:");
                stringGlassCost = myScanner.nextLine();
                glassCost = Float.parseFloat(stringGlassCost);

                if (glassCost >= 1) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid entry!");
            }
        } while (!isValid);

        isValid = false;

        //trim cost input
        do {
            try {
                System.out.println("Please enter the trim cost per linear foot:");
                stringTrimCost = myScanner.nextLine();
                trimCost = Float.parseFloat(stringTrimCost);

                if (trimCost >= 1) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid entry!");
            }
        } while (!isValid);

        isValid = false;

        //number of windows input
        do {
            try {
                System.out.println("Please enter the number of windows you have:");
                stringWindowNumber = myScanner.nextLine();
                windowNumber = Integer.parseInt(stringWindowNumber);

                if (windowNumber >= 1) {
                    isValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid entry!");
            }
        } while (!isValid);

        // calculate the area of the window
        areaOfWindow = height * width;

        // calculate the perimeter of the window
        perimeterOfWindow = (height + width)* 2;

        // calculate the total cost
        totalCost = windowNumber * ((glassCost * areaOfWindow) + (trimCost * perimeterOfWindow));

        //printing out the results
        System.out.println("Window height = " + stringHeight);
        System.out.println("Window width = " + stringWidth);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Window number = " + stringWindowNumber);
        System.out.println("Glass cost = " + stringGlassCost + "$ per square foot");
        System.out.println("Trim cost = " + stringTrimCost + "$ per linear foot");
        System.out.println("Total Cost = " + totalCost);
    }
}
