package fors;

public class ForByFor {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {//need to refactor this later
            System.out.print("|");

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("*");
                }
                System.out.print("|");
            }
            System.out.println("");
        }
    }
}
