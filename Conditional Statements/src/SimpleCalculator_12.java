import javax.swing.text.html.CSS;
import java.util.Scanner;

public class SimpleCalculator_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = Double.parseDouble(scanner.nextLine()),
                b = Double.parseDouble(scanner.nextLine()),
        result = 0;
        String act =  scanner.nextLine();

        switch (act) {
            case "add":
                result = a + b;
                System.out.printf("Result: %.2f" + result);
                break;

            case "substract":
                result = a - b;
                System.out.printf("Result: %.2f" + result);
                break;

            case "divide":
                result = a / b;
                System.out.printf("Result: %.2f" + result);
                break;

            case "multiply":
                result = a * b;
                System.out.printf("Result: %.2f" + result);
                break;
        }

        scanner.close();
    }
}
