import java.util.Scanner;

public class MaximalSum_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix[i][j] = scanner.nextInt();
        }

        int maxSum = Integer.MIN_VALUE;
        int bestRow = 0;
        int bestCol = 0;

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {
                int currentSum = 0;

                for (int row = i; row < i + 3; row++) {
                    for (int col = j; col < j + 3; col++)
                        currentSum += matrix[row][col];
                }

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    bestRow = i;
                    bestCol = j;
                }
            }
        }

        System.out.println("Sum = " + maxSum);
        for (int i = bestRow; i < bestRow + 3; i++) {
            for (int j = bestCol; j < bestCol + 3; j++)
                System.out.print(matrix[i][j] + " ");

            System.out.println();
        }
    }
}
