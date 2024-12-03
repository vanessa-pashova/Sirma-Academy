import java.util.Scanner;

public class MagicSquareChecker_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                matrix[i][j] = scanner.nextInt();
        }

        if (isMagicSquare(matrix, n))
            System.out.println("true");

        else
            System.out.println("false");
    }

    public static boolean isMagicSquare(int[][] matrix, int n) {
        int magicSum = 0;
        for (int j = 0; j < n; j++)
            magicSum += matrix[0][j];


        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++)
                rowSum += matrix[i][j];

            if (rowSum != magicSum)
                return false;

        }

        for (int j = 0; j < n; j++) {
            int colSum = 0;

            for (int i = 0; i < n; i++)
                colSum += matrix[i][j];

            if (colSum != magicSum)
                return false;
        }

        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int i = 0; i < n; i++) {
            diagonal1 += matrix[i][i];
            diagonal2 += matrix[i][n - i - 1];
        }

        return diagonal1 == magicSum && diagonal2 == magicSum;
    }
}
