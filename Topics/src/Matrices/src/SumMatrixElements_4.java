import java.util.Scanner;

public class SumMatrixElements_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(", ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = new int[rows][cols];
        int sum = 0;

        for (int i = 0; i < rows; i++) {
            String[] rowElements = scanner.nextLine().split(", ");

            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(rowElements[j]);
                sum += matrix[i][j];
            }
        }

        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
    }
}
