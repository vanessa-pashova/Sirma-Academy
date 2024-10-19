import java.util.Scanner;

public class FillTheMatrix_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char patternType = scanner.next().charAt(0);

        int[][] matrix = new int[n][n];
        if (patternType == 'A')
            fillMatrixPatternA(matrix, n);

        else if (patternType == 'B')
            fillMatrixPatternB(matrix, n);

        printMatrix(matrix);
    }

    private static void fillMatrixPatternA(int[][] matrix, int n) {
        int num = 1;

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++)
                matrix[row][col] = num++;
        }
    }

    private static void fillMatrixPatternB(int[][] matrix, int n) {
        int num = 1;

        for (int col = 0; col < n; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < n; row++)
                    matrix[row][col] = num++;
            }

            else {
                for (int row = n - 1; row >= 0; row--)
                    matrix[row][col] = num++;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] rows : matrix) {
            for (int value : rows)
                System.out.print(value + " ");

            System.out.println();
        }
    }
}
