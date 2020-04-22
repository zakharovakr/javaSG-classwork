import java.util.Scanner;
import java.util.Locale;
import java.text.NumberFormat;

public class InterestCalculator {
    public static void main(String[] args) {
        float principalAmount, initialAmount, annualRate, interest, annualInterest, quarterlyInterest;
        int yearAmount;

        Scanner scan = new Scanner(System.in);

        //currency formatting
        Locale currentLocale = Locale.getDefault();
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);


        System.out.println("How much do you want to invest?");
        principalAmount = Float.parseFloat(scan.nextLine());

        System.out.println("How many years are investing?");
        yearAmount = Integer.parseInt(scan.nextLine());

        System.out.println("What is the annual interest rate % growth?");
        annualRate = Float.parseFloat(scan.nextLine());

        System.out.println("Calculating...");


        for (int i = 1; i <= yearAmount; i++) {
            System.out.println("Year " + i + ":");
            System.out.println("Began with: " + currencyFormatter.format(principalAmount));
            
            interest = 0;
            initialAmount = principalAmount;


            for (int j = 1; j <=4; j++) {
                interest = (initialAmount * (1 + annualRate/4/100)) - initialAmount;
                initialAmount += interest;
            }

            annualInterest = initialAmount - principalAmount;


            System.out.println("Earned: " + currencyFormatter.format(annualInterest));

            principalAmount += annualInterest;
            System.out.println("Ended with: " + currencyFormatter.format(principalAmount));
            System.out.println("\n");
        }

    }
}
