import java.util.Scanner;

public class FactorialCalculation_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine()),
                fact = 1;

        for(int i = 1; i <= num; i++)
            fact *= i;

        System.out.printf("Factorial of %d: %d", num, fact);
    }
}
