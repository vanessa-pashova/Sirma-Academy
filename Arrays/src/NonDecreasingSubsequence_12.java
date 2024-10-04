import java.util.Scanner;

public class NonDecreasingSubsequence_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(",");
        int biggest = Integer.parseInt(input[0]);

        System.out.print(biggest + " ");

        for (int i = 1; i < input.length; i++) {
            int current = Integer.parseInt(input[i]);

            if (current >= biggest) {
                System.out.print(current + " ");
                biggest = current;
            }
        }

        scanner.close();
    }
}
