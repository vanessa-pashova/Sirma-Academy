import java.util.Scanner;

public class LastKNumbers_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(),
                k = scanner.nextInt();

        long[] seq = new long[n];
        seq[0] = 1;

        for (int i = 1; i < n; i++) {
            long sum = 0;

            for (int j = i - k; j < i; j++) {
                if (j >= 0)
                    sum += seq[j];
            }

            seq[i] = sum;
        }

        for (long num : seq)
            System.out.print(num + " ");

        scanner.close();
    }
}
