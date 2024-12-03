import java.util.Scanner;

public class Calculator_4 {

    public static void add(int num1, int num2) {
        System.out.println(num1 + num2);
    }

    public static void subtract(int num1, int num2) {
        System.out.println(num1 - num2);
    }

    public static void multiply(int num1, int num2) {
        System.out.println(num1 * num2);
    }

    public static void divide(int num1, int num2) {
        if (num2 != 0)
            System.out.println(num1 / num2);

        else
            System.out.println("Division by zero is not allowed.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String operation = scanner.nextLine();
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        switch (operation) {
            case "add":
                add(num1, num2);
                break;

            case "subtract":
                subtract(num1, num2);
                break;

            case "multiply":
                multiply(num1, num2);
                break;

            case "divide":
                divide(num1, num2);
                break;

            default:
                System.out.println("Invalid operation");
                break;
        }
    }
}
