import java.util.Scanner;

public class NumbersTo1000EndingInN_23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= 1000; i++) {
            if (i % 10 == n)
                System.out.println(i);
        }

        scanner.close();
    }
}
