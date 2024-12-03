import java.util.Scanner;

public class ArmstrongNumber_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine()),
                countOfDigits = 0, temp = num, sum = 0;

        while(temp != 0) {
            temp /= 10;
            countOfDigits++;
        }

        temp = num;
        while(temp != 0) {
            int prod = 1;
            for(int i = 0; i < countOfDigits; i++)
                prod *= (temp % 10);

            sum += prod;
            temp /= 10;
        }

        System.out.println((sum == num) ? "Armstrong" : "Nope");
        scanner.close();
    }
}
