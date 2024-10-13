import java.util.Scanner;

public class MultiplyEvensByOdds_10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Math.abs(Integer.parseInt(scanner.nextLine()));
        int result = getEvenSum(number) * getOddSum(number);
        System.out.println(result);
    }

    public static int getEvenSum(int number) {
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;

            if (digit % 2 == 0)
                sum += digit;

            number /= 10;
        }

        return sum;
    }

    public static int getOddSum(int number) {
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;

            if (digit % 2 != 0)
                sum += digit;

            number /= 10;
        }

        return sum;
    }
}
