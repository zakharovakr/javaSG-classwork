public class SummativeSums {

    // method for sum of elements in an array
    public static int arraySum(int array[]) {
        int sum = 0; // initialize sum

        // iterate through all elements and add them to sum
        for (int i = 0; i < array.length; i++)
            sum +=  array[i];

        return sum;
    }

    public static void main(String[] args) {
        //printing the results to the console
        System.out.println("The sum of array #1 is: " + arraySum(new int[]{1, 90, -33, -55, 67, -16, 28, -55, 15}));
        System.out.println("The sum of array #2 is: " + arraySum(new int[]{ 999, -60, -77, 14, 160, 301 }));
        System.out.println("The sum of array #3 is: " + arraySum(new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99 }));
    }
}
