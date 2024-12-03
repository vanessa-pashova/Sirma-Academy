import java.util.Scanner;

public class CoinsAndNotes_25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int coins1lv = Integer.parseInt(scanner.nextLine());
        int coins2lv = Integer.parseInt(scanner.nextLine());
        int banknotes5lv = Integer.parseInt(scanner.nextLine());
        int targetSum = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <= coins1lv; i++) {
            for (int j = 0; j <= coins2lv; j++) {
                for (int k = 0; k <= banknotes5lv; k++) {
                    int currentSum = i * 1 + j * 2 + k * 5;

                    if (currentSum == targetSum)
                        System.out.printf("%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.%n", i, j, k, targetSum);
                }
            }
        }

        scanner.close();
    }
}
