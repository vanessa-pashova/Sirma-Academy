import java.util.Scanner;

public class MaximumSum2x2Submatrix_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(", ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] rowElements = scanner.nextLine().split(", ");

            for (int j = 0; j < cols; j++)
                matrix[i][j] = Integer.parseInt(rowElements[j]);
        }

        int maxSum = Integer.MIN_VALUE;
        int[][] bestSubmatrix = new int[2][2];

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                int currentSum = matrix[i][j] + matrix[i][j + 1]
                        + matrix[i + 1][j] + matrix[i + 1][j + 1];

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    bestSubmatrix[0][0] = matrix[i][j];
                    bestSubmatrix[0][1] = matrix[i][j + 1];
                    bestSubmatrix[1][0] = matrix[i + 1][j];
                    bestSubmatrix[1][1] = matrix[i + 1][j + 1];
                }
            }
        }

        System.out.println(bestSubmatrix[0][0] + " " + bestSubmatrix[0][1]);
        System.out.println(bestSubmatrix[1][0] + " " + bestSubmatrix[1][1]);
        System.out.println(maxSum);
    }
}
