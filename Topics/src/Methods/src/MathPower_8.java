import java.util.Scanner;

public class MathPower_8 {

    public static double calculatePower(double number, int power) {
        return Math.pow(number, power);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double number = scanner.nextDouble();
        int power = scanner.nextInt();

        double result = calculatePower(number, power);
        System.out.printf("%.2f", result);
    }
}
