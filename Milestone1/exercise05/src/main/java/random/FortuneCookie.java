package random;

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {
        Random randomizer = new Random();

        System.out.println("Let's see what the fortune cookie tells you...");

        int fortune = randomizer.nextInt(10) + 1; //1-10

        switch (fortune) {
            case 1: {
                System.out.println("Don't gaze into the abyss too long. It'll get mildly upset");
                break;
            }
            case 2: {
                System.out.println("Broke: cereal before milk. Woke: bowl, then milk, then cereal");
                break;
            }
            case 3: {
                System.out.println("Count your change before change counts you");
                break;
            }
            case 4: {
                System.out.println("Don't mess with the emu's. They defeated Australia, they will gladly beat you");
                break;
            }
            case 5: {
                System.out.println("Drive on the right side of the road. Unless you live somewhere where the law says stay left");
                break;
            }
            case 6: {
                System.out.println("Drink water. Now.");
                break;
            }
            case 7: {
                System.out.println("They approach. Their intentions are unknown");
                break;
            }
            case 8: {
                System.out.println("Walk silently but carry a big stick. The stick should fit the polo association's regulations");
                break;
            }
            case 9: {
                System.out.println("All will be returned soon enough. Settle your debts in this world while you can");
                break;
            }
            case 10: {
                System.out.println("Everyone asks me \"where the gardening hoes at\" like I'm supposed to know or work at home depot. "
                        + "No one even knows who let them out of the prison unmoved by time of the 53rd dimension");
                break;
            }
            default: {
                System.out.println("Damn son where'd ya find this?");
                break;
            }
        }
    }
}
