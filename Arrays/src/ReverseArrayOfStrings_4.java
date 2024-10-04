import java.util.Scanner;

public class ReverseArrayOfStrings_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArray = scanner.nextLine().split(",");

        for (int i = inputArray.length - 1; i >= 0; i--)
            System.out.print(inputArray[i] + " ");

        scanner.close();
    }
}
