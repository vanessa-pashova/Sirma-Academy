import java.util.Scanner;

public class RecursiveFibonacci_14 {

    public static long getFibonacci(int x) {
        if (x == 0)
            return 0;

        if (x == 1)
            return 1;

        return getFibonacci(x - 1) + getFibonacci(x - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(getFibonacci(num));
    }
}
