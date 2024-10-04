import java.util.Scanner;

public class ReverseArray_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] inputArray = scanner.nextLine().split(" ");

        for (int i = n - 1; i >= 0; i--)
            System.out.print(inputArray[i] + " ");

        scanner.close();
    }
}
