import java.util.Scanner;

public class BiggerBetterAdder {
    public static void main(String[] args) {
        int num1, num2, num3, sum;

        /*num1 = 5;
        num2 = 7;
        num3 = 76;*/
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Tell me a number:");
        num1 = Integer.parseInt(myScanner.nextLine());

        System.out.println("Tell me a second number:");
        num2 = Integer.parseInt(myScanner.nextLine());

        System.out.println("Tell me a third number:");
        num3 = Integer.parseInt(myScanner.nextLine());

        sum = num1 + num2 + num3;

        System.out.print(num1);
        System.out.print("+" + num2);
        System.out.print("+" + num3);
        System.out.print("=" + sum);
    }
}