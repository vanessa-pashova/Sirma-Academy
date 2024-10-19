import java.util.Scanner;

public class MatrixAddition_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix1 = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix1[i][j] = scanner.nextInt();
        }

        int[][] matrix2 = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix2[i][j] = scanner.nextInt();
        }

        int[][] resultMatrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                System.out.print(resultMatrix[i][j] + " ");
            System.out.println();
        }
    }
}
