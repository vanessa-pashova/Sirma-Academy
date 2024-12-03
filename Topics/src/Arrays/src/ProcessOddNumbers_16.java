import java.util.Scanner;

public class ProcessOddNumbers_16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(",");
        int[] numbers = new int[input.length];

        for (int i = 0; i < input.length; i++)
            numbers[i] = Integer.parseInt(input[i]);

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (i % 2 != 0)
                System.out.print(numbers[i] * 2 + " ");
        }

        scanner.close();
    }
}
