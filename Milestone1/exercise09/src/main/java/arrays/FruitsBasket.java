package arrays;

public class FruitsBasket {
    public static void main(String[] args) {
            String[] fruitBasket = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple",
                    "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange",
                    "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple",
                    "Orange", "Orange", "Apple", "Apple", "Apple", "Banana", "Apple", "Orange",
                    "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple",
                    "Apple", "Apple", "Apple", "Orange", "Orange", "PawPaw", "Apple", "Orange",
                    "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange",
                    "Apple", "Orange", "Apple", "Kiwi", "Orange", "Apple", "Orange",
                    "Dragonfruit", "Apple", "Orange", "Orange"};

            int numOranges = 0;
            int numApples = 0;
            int numOtherFruit = 0;
            int totalFruitNum = fruitBasket.length;

            // Fruit counting code goes here!

            for (int i = 0; i < fruitBasket.length; i++) {
                if (fruitBasket[i] == "Apple") {
                    numApples++;
                } else if (fruitBasket[i] == "Orange") {
                    numOranges++;
                } else {
                    numOtherFruit++;
                }
            }

            // Print The Results!

        System.out.println("Total number of fruits = " + totalFruitNum);
        System.out.println("Number of oranges = " + numOranges);
        System.out.println("Number of apples = " + numApples);
        System.out.println("Number of other fruits = " + numOtherFruit);

    }
}
