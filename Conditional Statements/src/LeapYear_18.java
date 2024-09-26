import java.util.Scanner;

public class LeapYear_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a year: ");
        int year = scanner.nextInt();

        boolean isLeapYear = false;

        if (year % 4 == 0) {
            if (year % 100 != 0 || year % 400 == 0)
                isLeapYear = true;
        }

        if (isLeapYear)
            System.out.println("It's a leap year!");

        else
            System.out.println("It's not a leap year.");

        scanner.close();
    }
}
