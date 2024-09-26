import java.util.Scanner;

public class MagicNumber_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = Integer.parseInt(scanner.nextLine()),
                end = Integer.parseInt(scanner.nextLine()),
                magicNumber = Integer.parseInt(scanner.nextLine()),
                combinationsCount = 0;

        boolean isFound = false;

        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                combinationsCount++;
                if (i + j == magicNumber) {
                    System.out.printf("Combination %d - (%d + %d = %d)%n", combinationsCount, i, j, magicNumber);
                    isFound = true;
                    break;
                }
            }

            if (isFound)
                break;
        }

        if (!isFound)
            System.out.printf("%d combinations - neither equals %d%n", combinationsCount, magicNumber);

        scanner.close();
    }
}
