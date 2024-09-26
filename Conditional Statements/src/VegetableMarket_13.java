import java.util.Scanner;

public class VegetableMarket_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String product = scanner.nextLine(),
                weekday = scanner.nextLine();
        int quantity = Integer.parseInt(scanner.nextLine());
        double price = 0, total = 0;

        if (weekday.equals("Saturday") || weekday.equals("Sunday")) {
            switch (product) {
                case "tomato":
                    price = 2.8;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "onion":
                    price = 1.3;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "lettuce":
                    price = 0.85;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "cucumber":
                    price = 1.75;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "papper":
                    price = 3.5;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                default:
                    System.out.println("Wrong product inserted\n");
                    break;
            }
        } else if (weekday.equals("Monday") || weekday.equals("Tuesday") || weekday.equals("Wednesday")
                || weekday.equals("Thuursday") || weekday.equals("Friday")) {
            switch (product) {
                case "tomato":
                    price = 2.5;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "onion":
                    price = 1.2;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "lettuce":
                    price = 0.85;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "cucumber":
                    price = 1.45;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                case "papper":
                    price = 5.5;
                    total = price * quantity;
                    System.out.printf("Total: %.2f", total);
                    break;

                default:
                    System.out.println("Wrong product inserted\n");
                    break;
            }
        }

        scanner.close();
    }
}
