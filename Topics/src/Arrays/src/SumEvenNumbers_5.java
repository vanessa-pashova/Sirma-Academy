import java.util.Scanner;

public class SumEvenNumbers_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArray = scanner.nextLine().split(",");

        int sum = 0;

        for (String element : inputArray) {
            int number = Integer.parseInt(element);

            if (number % 2 == 0)
                sum += number;
        }

        System.out.println(sum);
        scanner.close();
    }
}
