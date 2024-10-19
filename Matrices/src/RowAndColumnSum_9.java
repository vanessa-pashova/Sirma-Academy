import java.util.Scanner;

public class RowAndColumnSum_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
                matrix[row][col] = scanner.nextInt();
        }

        for (int row = 0; row < rows; row++) {
            int rowSum = 0;

            for (int col = 0; col < cols; col++)
                rowSum += matrix[row][col];

            System.out.println("Sum of row " + (row + 1) + ": " + rowSum);
        }

        for (int col = 0; col < cols; col++) {
            int colSum = 0;

            for (int row = 0; row < rows; row++)
                colSum += matrix[row][col];

            System.out.println("Sum of column " + (col + 1) + ": " + colSum);
        }
    }
}
