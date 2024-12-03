import java.util.Scanner;

public class ZeroMatrix_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        boolean[] rowZero = new boolean[rows];
        boolean[] colZero = new boolean[cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = scanner.nextInt();

                if (matrix[row][col] == 0) {
                    rowZero[row] = true;
                    colZero[col] = true;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (rowZero[row] || colZero[col])
                    matrix[row][col] = 0;
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++)
                System.out.print(matrix[row][col] + " ");

            System.out.println();
        }
    }
}
