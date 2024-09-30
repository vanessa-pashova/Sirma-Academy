import java.util.Scanner;

public class SpecialNumbers_47 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1111; i <= 9999; i++) {
            int currentNumber = i;
            boolean isSpecial = true;

            while (currentNumber > 0) {
                int digit = currentNumber % 10;

                if (digit == 0 || n % digit != 0) {
                    isSpecial = false;
                    break;
                }

                currentNumber /= 10;
            }

            if (isSpecial)
                System.out.print(i + " ");
        }

        scanner.close();
    }
}
