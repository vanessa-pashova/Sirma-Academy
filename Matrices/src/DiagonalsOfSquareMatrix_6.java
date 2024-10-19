import java.util.Scanner;

public class DiagonalsOfSquareMatrix_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] rowElements = scanner.nextLine().split(" ");

            for (int j = 0; j < n; j++)
                matrix[i][j] = Integer.parseInt(rowElements[j]);
        }

        for (int i = 0; i < n; i++)
            System.out.print(matrix[i][i] + " ");

        System.out.println();

        for (int i = 0; i < n; i++)
            System.out.print(matrix[n - 1 - i][i] + " ");
    }
}
