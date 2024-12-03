import java.util.Scanner;

public class EvenPairs_26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startFirstPair = Integer.parseInt(scanner.nextLine());
        int startSecondPair = Integer.parseInt(scanner.nextLine());
        int diffFirstPair = Integer.parseInt(scanner.nextLine());
        int diffSecondPair = Integer.parseInt(scanner.nextLine());

        int endFirstPair = startFirstPair + diffFirstPair;
        int endSecondPair = startSecondPair + diffSecondPair;

        for (int i = startFirstPair; i <= endFirstPair; i++) {
            for (int j = startSecondPair; j <= endSecondPair; j++) {
                boolean isFirstPrime = true;
                boolean isSecondPrime = true;

                if (i <= 1)
                    isFirstPrime = false;

                for (int k = 2; k <= Math.sqrt(i); k++) {
                    if (i % k == 0) {
                        isFirstPrime = false;
                        break;
                    }
                }

                if (j <= 1)
                    isSecondPrime = false;

                for (int k = 2; k <= Math.sqrt(j); k++) {
                    if (j % k == 0) {
                        isSecondPrime = false;
                        break;
                    }
                }

                if (isFirstPrime && isSecondPrime)
                    System.out.printf("%d%d%n", i, j);
            }
        }

        scanner.close();
    }
}
