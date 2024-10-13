import java.util.Scanner;

public class Shop_5 {

    public static void calculatePrice(String product, int quantity) {
        double price = 0;
        switch (product) {
            case "coffee":
                price = 1.50;
                break;

            case "water":
                price = 1.00;
                break;

            case "coke":
                price = 1.40;
                break;

            case "snacks":
                price = 2.00;
                break;
        }

        double totalPrice = price * quantity;
        System.out.printf("%.2f", totalPrice);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String product = scanner.nextLine();
        int quantity = scanner.nextInt();

        calculatePrice(product, quantity);
    }
}
