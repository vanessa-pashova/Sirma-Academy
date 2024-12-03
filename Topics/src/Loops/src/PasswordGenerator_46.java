import java.util.Scanner;

public class PasswordGenerator_46 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int l = Integer.parseInt(scanner.nextLine());

        for (int digit1 = 1; digit1 <= n; digit1++) {
            for (int digit2 = 1; digit2 <= n; digit2++) {
                for (char letter1 = 'a'; letter1 < 'a' + l; letter1++) {
                    for (char letter2 = 'a'; letter2 < 'a' + l; letter2++) {
                        for (int digit3 = 1; digit3 <= n; digit3++) {
                            if (digit3 > digit1 && digit3 > digit2)
                                System.out.printf("%d%d%c%c%d ", digit1, digit2, letter1, letter2, digit3);
                        }
                    }
                }
            }
        }

        scanner.close();
    }
}
