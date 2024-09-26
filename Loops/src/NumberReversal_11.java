import java.util.Scanner;

public class NumberReversal_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        while(num != 0) {
            System.out.print((num % 10));
            num /= 10;
        }

        scanner.close();
    }
}
