package arrays;

public class SimpleCombination {

    public static int[] combineArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

        //copy a
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        //copy b
        for (int i = 0; i < b.length; i++) {
            c[a.length + i] = b[i];
        }
        return c;
    }

    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 35, 45, 47, 49}; // 12 numbers
        int[] secondHalf = {51, 54, 68, 71, 75, 78, 82, 84, 85, 89, 91, 100}; // also 12!

        int[] wholeNumbers = new int[24];

        wholeNumbers = combineArrays(firstHalf, secondHalf);

        System.out.println("All together now!:");

        for (int i = 0; i < wholeNumbers.length; i++) {
            System.out.print(wholeNumbers[i] + " ");
        }
    }
}
