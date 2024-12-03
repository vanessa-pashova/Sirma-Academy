import java.util.Scanner;

public class PyramidOfNumbers_28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int currentNumber = 1;

        for (int row = 1; currentNumber <= n; row++) {
            for (int col = 1; col <= row; col++) {
                if (currentNumber > n)
                    break;

                System.out.print(currentNumber + " ");
                currentNumber++;
            }

            System.out.println();
        }

        scanner.close();
    }
}
