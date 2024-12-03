import java.util.Scanner;

public class UniqueCodes_29 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int upperLimitFirst = Integer.parseInt(scanner.nextLine());
        int upperLimitSecond = Integer.parseInt(scanner.nextLine());
        int upperLimitThird = Integer.parseInt(scanner.nextLine());

        for (int first = 2; first <= upperLimitFirst; first += 2) {
            for (int second = 2; second <= upperLimitSecond; second++) {
                if (second == 2 || second == 3 || second == 5 || second == 7) {
                    for (int third = 2; third <= upperLimitThird; third += 2)
                        System.out.printf("%d%d%d%n", first, second, third);
                }
            }
        }

        scanner.close();
    }
}
