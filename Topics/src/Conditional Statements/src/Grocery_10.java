import javax.swing.text.html.CSS;
import java.util.Scanner;

public class Grocery_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String product = scanner.nextLine(),
                city = scanner.nextLine();
        int quantity = Integer.parseUnsignedInt(scanner.nextLine());
        double price = 0, total = 0;

        switch (product) {
            case "tea":
                switch (city) {
                    case "Sofia":
                        price = 0.5;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Plovdiv":
                        price = 0.4;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Varna":
                        price = 0.45;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    default:
                        System.out.println("Wrong city given\n");
                        break;
                }
                break;

            case "water":
                switch (city) {
                    case "Sofia":
                        price = 0.8;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Plovdiv":
                        price = 0.7;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Varna":
                        price = 0.7;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    default:
                        System.out.println("Wrong city given\n");
                        break;
                }
                break;

            case "juice":
                switch (city) {
                    case "Sofia":
                        price = 1.2;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Plovdiv":
                        price = 1.15;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Varna":
                        price = 1.1;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    default:
                        System.out.println("Wrong city given\n");
                        break;
                }
                break;

            case "sweets":
                switch (city) {
                    case "Sofia":
                        price = 1.45;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Plovdiv":
                        price = 1.3;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Varna":
                        price = 1.35;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    default:
                        System.out.println("Wrong city given\n");
                        break;
                }
                break;

            case "chips":
                switch (city) {
                    case "Sofia":
                        price = 1.6;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Plovdiv":
                        price = 1.5;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;

                    case "Varna":
                        price = 1.55;
                        total = price * quantity;
                        System.out.printf("Total: %.2f\n", total);
                        break;


                    default:
                        System.out.println("Wrong city given\n");
                        break;
                }
                break;

            default:
                System.out.println("Wrong product given\n");
                break;
        }

        scanner.close();
    }
}
