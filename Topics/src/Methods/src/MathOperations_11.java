import java.util.Scanner;

public class MathOperations_11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double num1 = Double.parseDouble(scanner.nextLine());
        String operator = scanner.nextLine();
        double num2 = Double.parseDouble(scanner.nextLine());

        double result = calculate(num1, operator, num2);
        System.out.printf("%.2f", result);
    }

    public static double calculate(double num1, String operator, double num2) {
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;

            case "-":
                result = num1 - num2;
                break;

            case "*":
                result = num1 * num2;
                break;

            case "/":
                if (num2 != 0)
                    result = num1 / num2;

                else
                    System.out.println("Cannot divide by zero");

                break;
        }

        return result;
    }
}
