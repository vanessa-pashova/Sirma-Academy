import java.util.Scanner;

public class SpiralMatrixTraversal_19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                matrix[i][j] = scanner.nextInt();
        }

        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)
                System.out.print(matrix[top][i] + " ");

            top++;

            for (int i = top; i <= bottom; i++)
                System.out.print(matrix[i][right] + " ");

            right--;

            if (top <= bottom)
                for (int i = right; i >= left; i--) {
                    System.out.print(matrix[bottom][i] + " ");

                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    System.out.print(matrix[i][left] + " ");

                left++;
            }
        }
    }
}
