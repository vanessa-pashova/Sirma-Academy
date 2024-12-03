import java.util.Scanner;

public class RefactorSumOfOddNumbers_21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        int cur = 1;

        for (int i = 0; i < n; i++) {
            System.out.println(cur);
            sum += cur;
            cur += 2;
        }

        System.out.printf("Sum: %d%n", sum);
        scanner.close();
    }
}
