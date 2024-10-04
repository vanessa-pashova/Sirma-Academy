import java.util.Scanner;

public class LongestIdenticalSequence_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int longestLength = 1,
                currentLength = 1;
        String longestElement = input[0];

        for (int i = 1; i < input.length; i++) {
            if (input[i].equals(input[i - 1]))
                currentLength++;

            else
                currentLength = 1;

            if (currentLength >= longestLength) {
                longestLength = currentLength;
                longestElement = input[i];
            }
        }

        for (int i = 0; i < longestLength; i++)
            System.out.print(longestElement + " ");

        scanner.close();
    }
}
