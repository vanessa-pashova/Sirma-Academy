import java.util.Scanner;

public class DiscountCalculator_22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseUnsignedInt(scanner.nextLine());
        int discount = 0;
        String membership = scanner.nextLine();

        if (age < 18)
            discount = 10;

        else if (age <= 64) {
            if (membership.equals("yes"))
                discount = 20;

            else if (membership.equals("no"))
                discount = 10;
        }

        else
            discount = 30;

        System.out.printf("Discount: %d%%", discount);
        scanner.close();
    }
}
