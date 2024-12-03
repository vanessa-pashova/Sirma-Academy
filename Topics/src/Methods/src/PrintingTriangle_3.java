import java.util.Scanner;

public class PrintingTriangle_3 {

    public static void printLine(int start, int end) {
        for (int i = start; i <= end; i++)
            System.out.print(i + " ");

        System.out.println();
    }

    public static void printTriangle(int size) {
        for (int i = 1; i <= size; i++)
            printLine(1, i);

        for (int i = size - 1; i >= 1; i--)
            printLine(1, i);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        printTriangle(size);
    }
}
