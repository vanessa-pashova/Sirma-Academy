import java.util.Scanner;

public class MatrixBoundarySum_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix[i][j] = scanner.nextInt();
        }

        int sum = 0;

        for (int j = 0; j < cols; j++)
            sum += matrix[0][j];

        for (int j = 0; j < cols; j++)
            sum += matrix[rows - 1][j];

        for (int i = 1; i < rows - 1; i++) {
            sum += matrix[i][0];
            sum += matrix[i][cols - 1];
        }

        System.out.println(sum);
    }
}
