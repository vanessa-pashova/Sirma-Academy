import java.util.Scanner;

public class SumFirstLastElement_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] array = scanner.nextLine().split(" ");
        int first = Integer.parseInt(array[0]),
                last = Integer.parseInt(array[array.length - 1]),
                sum = first+ last;

        System.out.println(sum);
        scanner.close();
    }
}
