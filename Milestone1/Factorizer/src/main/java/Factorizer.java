import java.util.Scanner;

public class Factorizer {

    //method to find prime or not
    public static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0) {
                return false;
            }
        return true;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int userNum;
        int totalFactors = 0;
        int factorCount = 0;

        //asking user for input
        System.out.println("What number would you like to factor?");

        userNum = Integer.parseInt(scan.nextLine());

        System.out.println("The factors of " + userNum + " are:");

        //finding the factors of the number
        for(int i = 1; i <= userNum; ++i) {
            if (userNum % i == 0) {
                System.out.print(i + " ");
                factorCount++;
                totalFactors = totalFactors + i;
            }
        }

        //printing out the total number of factors
        System.out.println("\n" + userNum + " has " + factorCount + " factors.");

        //perfect or not
        if (userNum == totalFactors) {
            System.out.println(userNum + "is a perfect number.");
        } else {
            System.out.println(userNum + " is not a perfect number.");
        }

        //prime or not (using a method)
        if (isPrime(userNum)) {
            System.out.println(userNum + " is a prime number.");
        } else {
            System.out.println(userNum + " is not a prime number.");
        }

    }
}
//maybe come back later and refactor with methods 