import java.util.Scanner;

public class ChessboardChecker_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                board[i][j] = scanner.nextInt();
        }

        System.out.println(areQueensThreatening(board) ? "yes" : "no");
    }

    public static boolean areQueensThreatening(int[][] board) {
        int n = board.length;

        for (int i = 0; i < n; i++) {
            int rowSum = 0, colSum = 0;

            for (int j = 0; j < n; j++) {
                rowSum += board[i][j];
                colSum += board[j][i];
            }

            if (rowSum > 1 || colSum > 1)
                return true;
        }

        for (int i = 0; i < 2 * n - 1; i++) {
            int diag1Sum = 0, diag2Sum = 0;

            for (int j = 0; j < n; j++) {
                int k = i - j;

                if (k >= 0 && k < n)
                    diag1Sum += board[j][k];

                if (i - (n - 1 - j) >= 0 && i - (n - 1 - j) < n)
                    diag2Sum += board[j][i - (n - 1 - j)];
            }

            if (diag1Sum > 1 || diag2Sum > 1)
                return true;
        }

        return false;
    }
}
