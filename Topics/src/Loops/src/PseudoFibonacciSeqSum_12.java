import java.util.Scanner;

public class PseudoFibonacciSeqSum_12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine()),
                first = 0, second = 1, sum = 1;

        for(int i = 2; i <= num; i++) {
            int next = first + second;
            sum += next;
            first = second;
            second = next;
        }

        System.out.printf("Sum: %d", sum);
        scanner.close();
    }
}
