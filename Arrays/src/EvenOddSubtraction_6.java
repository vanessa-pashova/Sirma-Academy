import java.util.Scanner;

public class EvenOddSubtraction_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split(",");

        int evenSum = 0,
                oddSum = 0;

        for (String element : inputArray) {
            int number = Integer.parseInt(element);

            if (number % 2 == 0)
                evenSum += number;

            else
                oddSum += number;
        }

        int result = evenSum - oddSum;
        System.out.println(result);
        scanner.close();
    }
}
