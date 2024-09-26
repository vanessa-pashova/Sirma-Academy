import java.util.Scanner;

public class SumOfEvenNumbers_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine()),
                sum = 0, num = 2;

        for(int i = input; i != 0; i--) {
            sum += num;
            num += 2;
        }

        System.out.printf("Sum: %d", sum);
        scanner.close();
    }
}
