import java.util.Scanner;

public class CompareMatrices_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows1 = scanner.nextInt();
        int cols1 = scanner.nextInt();
        int[][] matrix1 = new int[rows1][cols1];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++)
                matrix1[i][j] = scanner.nextInt();
        }

        int rows2 = scanner.nextInt();
        int cols2 = scanner.nextInt();
        int[][] matrix2 = new int[rows2][cols2];

        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++)
                matrix2[i][j] = scanner.nextInt();
        }

        if (rows1 != rows2 || cols1 != cols2) {
            System.out.println("Not equal\n");
            return;
        }

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    System.out.println("Not equal\n");
                    return;
                }
            }
        }

        System.out.println("Equal\n");
    }
}
