public class MenuOfChampions {
    public static void main(String[] args) {
        //declaring variables
        String menuItemOne;
        String menuItemTwo;
        String menuItemThree;

        float priceOne, priceTwo, priceThree;

        //assigning values
        menuItemOne = "Slice of Big Rico Pizza";
        menuItemTwo = "Invisible Strawberry Pie";
        menuItemThree = "Denver Omelet";

        priceOne = 500;
        priceTwo = 2;
        priceThree = (float) 1.50;

        //print the menu
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println("");
        System.out.println("WELCOME TO RESTAURANT NIGHT VALE!");
        System.out.println("Today's Menu Is...");
        System.out.println("");
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println("");
        System.out.println("$" + priceOne + " *** " + menuItemOne);
        System.out.println("$" + priceTwo + " ***** " + menuItemTwo);
        System.out.println("$" + priceThree + " ***** " + menuItemThree);
    }
}
