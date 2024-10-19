import java.util.Scanner;

public class MatrixBorderFlip_16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix[i][j] = scanner.nextInt();
        }

        flipBorder(matrix, rows, cols);
        printMatrix(matrix);
    }

    public static void flipBorder(int[][] matrix, int rows, int cols) {
        int[] border = new int[2 * rows + 2 * cols - 4];
        int index = 0;

        for (int j = 0; j < cols; j++)
            border[index++] = matrix[0][j];

        for (int i = 1; i < rows; i++)
            border[index++] = matrix[i][cols - 1];

        for (int j = cols - 2; j >= 0; j--)
            border[index++] = matrix[rows - 1][j];

        for (int i = rows - 2; i > 0; i--)
            border[index++] = matrix[i][0];

        int last = border[border.length - 1];
        System.arraycopy(border, 0, border, 1, border.length - 1);
        border[0] = last;

        index = 0;

        for (int j = 0; j < cols; j++)
            matrix[0][j] = border[index++];

        for (int i = 1; i < rows; i++)
            matrix[i][cols - 1] = border[index++];

        for (int j = cols - 2; j >= 0; j--)
            matrix[rows - 1][j] = border[index++];

        for (int i = rows - 2; i > 0; i--)
            matrix[i][0] = border[index++];
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row)
                System.out.print(element + " ");

            System.out.println();
        }
    }
}
