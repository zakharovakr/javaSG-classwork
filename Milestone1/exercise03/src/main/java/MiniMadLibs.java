import java.util.Scanner;

public class MiniMadLibs {

    public static void main(String[] args) {

        String firstNoun;
        String firstAdjective;
        String secondNoun;
        int number;
        String secondAdjective;
        String firstPluralNoun;
        String secondPluralNoun;
        String thirdPluralNoun;
        String verbPresentTense;
        String verbPastTense;

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Let's play MAD LIBS!");

        System.out.print("I need a noun: ");
        firstNoun = myScanner.nextLine();
        System.out.print("Now an adjective: ");
        firstAdjective = myScanner.nextLine();
        System.out.print("Another noun: ");
        secondNoun = myScanner.nextLine();
        System.out.print("And a number: ");
        number = Integer.parseInt(myScanner.nextLine());
        System.out.print("Another adjective: ");
        secondAdjective = myScanner.nextLine();

        System.out.print("A plural noun: ");
        firstPluralNoun = myScanner.nextLine();
        System.out.print("Another one: ");
        secondPluralNoun = myScanner.nextLine();
        System.out.print("One more: ");
        thirdPluralNoun = myScanner.nextLine();
        System.out.print("A verb (infinitive form): ");
        verbPresentTense = myScanner.nextLine();
        System.out.print("Same verb (past participle): ");
        verbPastTense = myScanner.nextLine();

        System.out.println("*** NOW LETS GET MAD (libs) ***");

        System.out.println(firstNoun + ": the " + firstAdjective + " frontier. These are the voyages of the starship " + secondNoun +
                ". Its " + number + "-year mission: to explore strange " + secondAdjective + " " + firstPluralNoun +
                ", to seek out " + secondAdjective + " " + secondPluralNoun + " and " + secondAdjective + " " + thirdPluralNoun +
                ", to boldly " + verbPresentTense + " where no one has " + verbPastTense + " before.");

    }
}