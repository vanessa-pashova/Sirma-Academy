import java.util.Scanner;

public class EqualSumOfOddAndEvenPositions_45 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());
        boolean found = false;

        for (int num = start; num <= end; num++) {
            String number = String.valueOf(num);
            int oddSum = 0;
            int evenSum = 0;

            for (int i = 0; i < number.length(); i++) {
                int digit = Character.getNumericValue(number.charAt(i));

                if (i % 2 == 0)
                    oddSum += digit;

                else
                    evenSum += digit;
            }

            if (oddSum == evenSum) {
                System.out.println(num);
                found = true;
            }
        }

        if (!found)
            System.out.println("None");

        scanner.close();
    }
}
