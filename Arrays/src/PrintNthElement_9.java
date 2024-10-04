import java.util.Scanner;

public class PrintNthElement_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(",");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < array.length; i += n)
            System.out.print(array[i] + " ");

        scanner.close();
    }
}
