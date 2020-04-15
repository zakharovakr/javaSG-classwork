public class TheOrderOfThings {
    public static void main(String[] args) {

        //In English, adjectives should normally appear in the order
        // number-opinion-size-age-shape-color-origin-material-purpose noun

        double number;
        String opinion, size, age, shape, color, origin, material, purpose;
        String noun;

        number = 5.0;
        opinion = "AWESOME";
        size = "teensy-weensy";
        age = "new";
        shape = "oblong";
        color = "yellow";
        origin = "Martian";
        material = "platinum";
        purpose = "good";

        noun = "dragons";

        // Using the + with strings doesn't add -- it concatenates! (sticks them together)
        System.out.println(number + " " + opinion + " " + size + " " + age + " " + shape
                + " " + color + " " + origin + " " + material + " " + purpose + " " + noun);
    }
}
