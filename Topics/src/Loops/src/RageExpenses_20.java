import java.util.Scanner;

public class RageExpenses_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lostGamesCount = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        int brokenHeadsets = 0;
        int brokenMice = 0;
        int brokenKeyboards = 0;
        int brokenDisplays = 0;

        for (int game = 1; game <= lostGamesCount; game++) {
            if (game % 2 == 0)
                brokenHeadsets++;

            if (game % 3 == 0)
                brokenMice++;

            if (game % 2 == 0 && game % 3 == 0) {
                brokenKeyboards++;

                if (brokenKeyboards % 2 == 0)
                    brokenDisplays++;
            }
        }

        double totalExpenses = (brokenHeadsets * headsetPrice) + (brokenMice * mousePrice) +
                (brokenKeyboards * keyboardPrice) + (brokenDisplays * displayPrice);

        System.out.printf("Rage expenses: %.2f lv.", totalExpenses);

        scanner.close();
    }
}
