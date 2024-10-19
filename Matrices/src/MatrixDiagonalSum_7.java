import java.util.Scanner;

public class MatrixDiagonalSum_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                matrix[i][j] = scanner.nextInt();
        }

        int sumPrimaryDiagonal = 0;
        int sumSecondaryDiagonal = 0;

        for (int i = 0; i < n; i++) {
            sumPrimaryDiagonal += matrix[i][i];
            sumSecondaryDiagonal += matrix[i][n - 1 - i];
        }

        System.out.println(sumPrimaryDiagonal + sumSecondaryDiagonal);
    }
}
