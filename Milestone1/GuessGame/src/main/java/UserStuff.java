import java.util.Scanner;

public class UserStuff {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What's your name?");
        String name = scan.nextLine();
        System.out.println("How old are you?");
        int years = scan.nextInt();

        int months = years*12;
        int days = years*365;
        int hours = days*24;
        long minutes = hours*60;
        long seconds = minutes*60;
        long mili = seconds*1000;

        System.out.println("Name: " + name);
        System.out.println("Age in years: " + years);
        System.out.println("Age in months: " + months);
        System.out.println("Age in days: " + days);
        System.out.println("Age in hours: " + hours);
        System.out.println("Age in minutes: " + minutes);
        System.out.println("Age in seconds: " + seconds);
        System.out.println("Age in miliseconds: " + mili);
    }
}
